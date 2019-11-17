package com.spring.assistant.assistant.todo.repository;

import com.spring.assistant.assistant.todo.entity.TodoEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TodoRepository extends CrudRepository<TodoEntity, Long> {

    TodoEntity findById(long id);
    List<TodoEntity> findByTaskId(String taskId);
    TodoEntity findByUserId(String userId);

    @Query("SELECT t FROM TodoEntity t WHERE t.userId=:userId")
    List<TodoEntity> finds(@Param("userId") String userId);
    /**
     *
     * BU YAPI BAYA ÖNEMLİ BURADAN BAK ARADA GÜZEL OLDU
     * */
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE TodoEntity t SET t.isFinnished = true, t.updatedDate=:updatedDate WHERE t.taskId=:taskId")
    int update(@Param("taskId") String taskId, @Param("updatedDate")LocalDate updatedDate);


    //TODO QUERY YAZMAYI DENE
   // @Query("select user_id from catgag.todo_entity where user_id= :user_id")
   // String queryReslut(@Param("user_id") String userId);

}
