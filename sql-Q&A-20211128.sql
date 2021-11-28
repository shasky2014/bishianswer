-- 1. 每个科目的前3名
select a.s_id,b.s_name,a.c_id,c.c_name,a.s_score
from (
    select s_id,c_id,s_score,row_number() over(partition by c_id order by s_score desc) as rk
    from score 
) a 
join  student as b on a.s_id = b.s_id
join  course as c on a.c_id = c.c_id
where a.rk <= 3;

-- 2. 按每人平均成绩高到低排序，列出所有科目成绩和均值
select a.*,b.s_name
from (
    select s_id, 
        sum(s_score) as summary, 
        avg(s_score) avg_score,
        sum(if(c_id='01',s_score,0)) as '语文',
        sum(if(c_id='02',s_score,0)) as '数学',
        sum(if(c_id='03',s_score,0)) as '英语'
    from score
    group by s_id
    order by avg_score desc
) a 
join student as b on a.s_id = b.s_id
;
-- 3. 每个科目的最高分最低分及格率(>=60算及格)，优秀率(>=90算优秀)，按选课人数倒排，科目id顺排
select c_id,max_score,min_score,jgl,yxl
from (
    select c_id, 
        count(s_id) s_count,
        max(s_score) max_score,
        min(s_score) min_score,
        count(if(s_score>=60,1,null))/count(s_id) as jgl,
        count(if(s_score>=90,1,null))/count(s_id) as yxl
    from score
    group by c_id
    order by s_count desc,c_id
) a ;

-- 4. 第一次下单后，第二次下单距离第一次下单的时间 
select uid, 
    if(next_order_time is null,null, getday(next_order_time-order_time)) as time_diff
from  (
    select uid,order_time,
        row_number() over(partition by uid,order by order_time) as rk,
        lead(order_time,1,null) over(partition by uid,order by order_time) as next_order_time
    from order_table
) a 
where rk = 1;

-- 5. 部门签到次数前10的人
select dep_name,user_id,sgin_count,rk
from (
    select dep_name,user_id,sgin_count,
        row_number() over(partition by dep_name,order by sgin_count desc) as rk
    from (
        select dep_name,user_id,count(if(is_sign=1,1,null)) as sgin_count
        from user_sign
        where date>='20201001' and date<='20201031'
        group by dep_name,user_id
    ) a 
) a 
where a.rk<=10

-- 6. 连续签到超过10天的人
select dep_name,user_id,lx_tag,count(date) sign_days
from (
    select dep_name,user_id,date,is_sign,day_diff(date,rk) lx_tag
    from (
        select dep_name,user_id,is_sign,date
            ,row_number() over(partition by dep_name,user_id,order by date) as rk
        from user_sign
        where date>='20201001' and date<='20201031'
        and is_sign = 1
    ) a 
) a 
group by dep_name,user_id,lx_tag
having count(date) > 10
