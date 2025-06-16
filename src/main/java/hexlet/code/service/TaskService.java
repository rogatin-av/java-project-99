package hexlet.code.service;

import hexlet.code.dto.task.TaskCreateDTO;
import hexlet.code.dto.task.TaskDTO;
import hexlet.code.dto.task.TaskParamsDTO;
import hexlet.code.dto.task.TaskUpdateDTO;
import hexlet.code.exception.ResourceNotFoundException;
import hexlet.code.mapper.TaskMapper;
import hexlet.code.repository.TaskRepository;
import hexlet.code.specification.TaskSpecification;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {

    private TaskRepository taskRepository;
    private TaskMapper taskMapper;
    private TaskSpecification specBuilder;

    public TaskDTO show(Long id) {
        return taskMapper.map(taskRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found")));
    }

    public List<TaskDTO> index(TaskParamsDTO params) {
        var spec = specBuilder.build(params);
        var allTask = taskRepository.findAll(spec);
        return allTask.stream()
                .map((tsk) -> taskMapper.map(tsk))
                .toList();

    }

    public TaskDTO create(TaskCreateDTO taskCreateDTO) {
        var task = taskMapper.map(taskCreateDTO);
        taskRepository.save(task);
        return taskMapper.map(task);
    }

    public TaskDTO update(long id, TaskUpdateDTO taskUpdateDTO) {
        var task = taskRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        taskMapper.update(taskUpdateDTO, task);
        taskRepository.save(task);
        return taskMapper.map(task);
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
