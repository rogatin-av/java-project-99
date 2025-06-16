package hexlet.code.service;

import hexlet.code.dto.label.LabelCreateDTO;
import hexlet.code.dto.label.LabelDTO;
import hexlet.code.dto.label.LabelUpdateDTO;
import hexlet.code.exception.ResourceDeletionException;
import hexlet.code.exception.ResourceNotFoundException;
import hexlet.code.mapper.LabelMapper;
import hexlet.code.repository.LabelRepository;
import hexlet.code.repository.TaskRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LabelService {

    private LabelMapper labelMapper;
    private LabelRepository labelRepository;
    private TaskRepository taskRepository;

    public LabelDTO show(Long id) {
        return labelMapper.map(labelRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Label not found")));
    }

    public List<LabelDTO> showAll() {
        var labels = labelRepository.findAll();
        return labels.stream()
                .map(labelMapper::map)
                .toList();
    }

    public LabelDTO create(LabelCreateDTO labelDTO) {
        return labelMapper.map(
                labelRepository.save(
                        labelMapper.map(labelDTO)));
    }

    public LabelDTO update(long id, LabelUpdateDTO dto) {
        var label = labelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Label not found"));
        labelMapper.update(dto, label);
        labelRepository.save(label);
        return labelMapper.map(label);
    }

    public void delete(long id) {
        var label = labelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Label not found"));
        if (taskRepository.existsByLabelsContaining(label)) {
            throw new ResourceDeletionException("Can't delete label");
        }
        labelRepository.deleteById(id);
    }
}
