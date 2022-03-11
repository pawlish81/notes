//package com.pwldata.services;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.contains;
//import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
//
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.DynamicPropertyRegistry;
//import org.springframework.test.context.DynamicPropertySource;
//import org.testcontainers.containers.MongoDBContainer;
//import org.testcontainers.containers.wait.strategy.Wait;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//
//import com.pwl.api.v1.model.Note.TagEnum;
//import com.pwldata.commons.TestObjectGenerator;
//import com.pwldata.domain.NoteDoc;
//import com.pwldata.exceptions.NoteNotFoundException;
//import com.pwldata.repositories.NotesRepository;
//
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@Testcontainers
//@DirtiesContext
//class NotesServiceTest {
//
//    @Autowired
//    NotesService notesService;
//
//    @Autowired
//    NotesRepository notesRepository;
//
//    @AfterEach
//    void cleanUp() {
//        this.notesRepository.deleteAll();
//    }
//
//    @Container
//    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:5.0.6")
//            .waitingFor(Wait
//                    .forLogMessage(".*Waiting for connections*.*", 1));
//
//    @DynamicPropertySource
//    static void setProperties(DynamicPropertyRegistry registry) {
//        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
//    }
//
//    @Test
//    void createNote() {
//        //given
//        NoteDoc note = TestObjectGenerator.generateObject(NoteDoc.class);
//
//        //when
//        NoteDoc persistedNote = notesService.createNote(note);
//
//        //then
//        assertThat(persistedNote.getId()).isNotEmpty();
//        assertThat(persistedNote)
//                .usingRecursiveComparison()
//                .ignoringFields("id")
//                .isEqualTo(note);
//
//    }
//
//    @Test
//    void deleteNote() {
//        //given
//        NoteDoc note = notesService.createNote(TestObjectGenerator.generateObject(NoteDoc.class));
//        String noteId = note.getId();
//        //when
//        notesService.deleteNote(note.getId());
//
//        //then
//        NoteNotFoundException exception = Assertions.assertThrows(NoteNotFoundException.class, () -> {
//            notesService.findNotesById(noteId);
//        });
//
//        assertThat(exception.getMessage()).isNotEmpty();
//        assertThat(exception.getMessage()).contains("Note with id " + noteId + " not found");
//    }
//
//    @Test
//    void findNotesById() {
//        //given
//        NoteDoc note = notesService.createNote(TestObjectGenerator.generateObject(NoteDoc.class));
//
//        //when
//        NoteDoc notesById = notesService.findNotesById(note.getId());
//
//        //then
//        assertThat(notesById.getId()).isNotNull();
//        assertThat(notesById)
//                .usingRecursiveComparison()
//                .isEqualTo(note);
//    }
//
//    @Test
//    void update() {
//        //given
//        NoteDoc note = TestObjectGenerator.generateObject(NoteDoc.class);
//        NoteDoc savedNote = notesService.createNote(note);
//        //when
//        savedNote.setText("One to Two and one text");
//        NoteDoc updatedNote = notesService.update(note);
//
//        //then
//        assertThat(updatedNote).isNotNull();
//        assertThat(updatedNote)
//                .usingRecursiveComparison()
//                .isEqualTo(savedNote);
//    }
//
//    @Test
//    void findAllWithNoSearchParameters() {
//        //given
//        Pageable paging = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "createDate"));
//        Stream<NoteDoc> notesList = TestObjectGenerator.getRandomizer(clazz).objects(NoteDoc.class, 10);
//        List<NoteDoc> expectedList = notesRepository.saveAll(notesList.collect(Collectors.toList()));
//        //when
//
//        Page<NoteDoc> resultList = notesService.findAll(new NoteDoc(), paging);
//        //then
//        assertThat(resultList).isNotNull();
//        assertThat(resultList.getTotalElements()).isEqualTo(expectedList.size());
//        //TO_DO fix that
////        assertTrue(resultList.getContent().containsAll(expectedList));
//    }
//
//
//    @Test
//    void findAllWithIdAsSearchParameter() {
//        //given
//        Pageable paging = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "createDate"));
//        Stream<NoteDoc> notesList = TestObjectGenerator.getRandomizer(clazz).objects(NoteDoc.class, 10);
//        List<NoteDoc> expectedList = notesRepository.saveAll(notesList.collect(Collectors.toList()));
//        NoteDoc noteDoc = expectedList.get(1);
//        //when
//
//        Page<NoteDoc> resultList = notesService.findAll(new NoteDoc().setId(noteDoc.getId()), paging);
//        //then
//        assertThat(resultList).isNotNull();
//        assertThat(resultList.getContent().size()).isEqualTo(1);
//    }
//
//
//    @Test
//    void findAllWithTagAsSearchParameter() {
//        //given
//        Pageable paging = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "createDate"));
//        Stream<NoteDoc> notesList = TestObjectGenerator.getRandomizer(clazz).objects(NoteDoc.class, 10);
//        List<NoteDoc> expectedList = notesRepository.saveAll(notesList.collect(Collectors.toList()));
//        //when
//
//        Page<NoteDoc> resultList = notesService.findAll(new NoteDoc().setTag(TagEnum.BUSINESS), paging);
//        //then
//        assertThat(resultList).isNotNull();
//        assertThat(resultList.getContent().size()).isEqualTo(expectedList.stream().filter(note -> note.getTag().equals(TagEnum.BUSINESS)).count());
//    }
//
//
//    @Test
//    void findAllWithTitleAsSearchParameter() {
//        //given
//        Pageable paging = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "createDate"));
//        Stream<NoteDoc> notesList = TestObjectGenerator.getRandomizer(clazz).objects(NoteDoc.class, 10);
//        List<NoteDoc> expectedList = notesRepository.saveAll(notesList.collect(Collectors.toList()));
//        String noteTitle = expectedList.get(0).getTitle();
//        //when
//        Page<NoteDoc> resultList = notesService.findAll(new NoteDoc().setTitle(noteTitle), paging);
//        //then
//        assertThat(resultList).isNotNull();
//        assertThat(resultList.getContent(), contains(
//                hasProperty("title", is(noteTitle))
//        ));
//    }
//
//
//    @Test
//    void findAllWithAllSearchParameter() {
//        //given
//        Pageable paging = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "createDate"));
//        Stream<NoteDoc> notesList = TestObjectGenerator.getRandomizer(clazz).objects(NoteDoc.class, 10);
//        List<NoteDoc> expectedList = notesRepository.saveAll(notesList.collect(Collectors.toList()));
//        NoteDoc noteDoc = expectedList.get(0);
//        System.out.println(noteDoc);
//        //when
//        Page<NoteDoc> resultList = notesService.findAll(noteDoc, paging);
//        //then
//        System.out.println(resultList.getContent().get(0));
//        assertThat(resultList).isNotNull();
//        assertThat(resultList.getContent().size()).isEqualTo(1);
//        assertThat(resultList.getContent(), contains(
//                hasProperty("id", is(noteDoc.getId()))
//        ));
//    }
//}