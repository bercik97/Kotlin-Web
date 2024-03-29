package pl.robert.kotlinweb.task

import lombok.AccessLevel
import lombok.experimental.FieldDefaults

import pl.robert.kotlinweb.task.domain.Task
import pl.robert.kotlinweb.task.domain.TaskService

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.beans.factory.annotation.Autowired

@FieldDefaults(level = AccessLevel.PRIVATE)
@RestController
@RequestMapping("api/task")
class TaskController @Autowired constructor(val service: TaskService) {

    @PostMapping
    fun save(@RequestBody task: Task) = ResponseEntity.ok(service.save(task))

    @GetMapping
    fun getAll() = ResponseEntity.ok(service.getAll())

    @GetMapping("{id}")
    fun getById(@PathVariable id: String) = ResponseEntity.ok(service.getById(id))

    @PutMapping("{id}")
    fun markAsDone(@PathVariable id: String) = ResponseEntity.ok(service.markAsDone(id))

    @DeleteMapping("{id}")
    fun deleteById(@PathVariable id: String) = ResponseEntity.ok(service.deleteById(id))

    @DeleteMapping
    fun deleteAll() = ResponseEntity.ok(service.deleteAll())
}
