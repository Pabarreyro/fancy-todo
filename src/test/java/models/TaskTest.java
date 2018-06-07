package models;

import org.junit.*;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class TaskTest {

    @Test
    public void newTask_instantiatesCorrectly_description() throws Exception {
        Task testTask = setupNewTask();
        assertEquals("Mow the lawn", testTask.getDescription());
    }

    @Test
    public void newTask_instantiatesWithCompletedSetToFalse_false() throws Exception {
        Task testTask = setupNewTask();
        assertEquals(false, testTask.isCompleted());
    }

    @Test
    public void newTask_instantiatesWithCurrentTimeToday_today() throws Exception {
    Task task = setupNewTask();
        assertEquals(LocalDateTime.now().getDayOfWeek(), task.getCreatedAt().getDayOfWeek());
    }

    //helper methods
    public Task setupNewTask(){
        return new Task("Mow the lawn", 1);
    }
}