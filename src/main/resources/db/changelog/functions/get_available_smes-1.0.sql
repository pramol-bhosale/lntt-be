
CREATE OR REPLACE FUNCTION public.get_available_smes(
    p_from_date VARCHAR,
    p_to_date VARCHAR,
    p_from_time VARCHAR,
    p_to_time VARCHAR
)
RETURNS TABLE (
    out_first_name VARCHAR,
    out_last_name VARCHAR
)
AS
$$
BEGIN
  RETURN QUERY
  WITH date_range AS (
      SELECT generate_series(p_from_date::date, p_to_date::date, interval '1 day')::date AS date
  ),

  user_daily_schedule AS (
      SELECT
          us.user_id,
          dr.date,
          us.from_time,
          us.to_time
      FROM public.schedules us
      JOIN date_range dr ON dr.date BETWEEN us.from_date AND us.to_date
  ),

  free_users_per_day AS (
      SELECT u.id, u.first_name, u.last_name, dr.date
      FROM public.users u
      CROSS JOIN date_range dr
      LEFT JOIN user_daily_schedule s
        ON u.id = s.user_id AND s.date = dr.date
      WHERE
          s.user_id IS NULL
          OR s.from_time >= p_to_time::time
          OR s.to_time <= p_from_time::time
  ),

  final_free_users AS (
      SELECT DISTINCT id, first_name, last_name
      FROM free_users_per_day
  )

  SELECT
    ffu.first_name,
    ffu.last_name
  FROM final_free_users ffu;

END;
$$
LANGUAGE plpgsql;