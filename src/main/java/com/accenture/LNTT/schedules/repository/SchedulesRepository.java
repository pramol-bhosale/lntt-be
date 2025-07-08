package com.accenture.LNTT.schedules.repository;

import com.accenture.LNTT.schedules.entity.AvailableSMEResultEntity;
import com.accenture.LNTT.schedules.entity.SchedulesEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulesRepository extends JpaRepository<SchedulesEntity, String> {
    @Query(value = "select * from public.schedules s  where s.user_id = :userId and from_date >= to_date(:month, 'YYYY-MM')  and to_date <= to_date(:month, 'YYYY-MM') + interval '1 month' - interval '1 day'", nativeQuery = true)
    Page<SchedulesEntity> findAllByUserAndInMonth(@Param(value = "userId") String userId, @Param(value = "month") String month, Pageable pageable);

    @Query(value = "select out_first_name as first_name , out_last_name as last_name from public.get_available_smes(:fromDate, :toDate, :fromTime, :toTime)", nativeQuery = true)
    Page<AvailableSMEResultEntity> findAvailableSMEs(@Param(value = "fromDate") String fromDate,@Param(value = "toDate") String toDate,@Param(value = "fromTime") String fromTime,@Param(value = "toTime") String toTime, Pageable pageable);
}
