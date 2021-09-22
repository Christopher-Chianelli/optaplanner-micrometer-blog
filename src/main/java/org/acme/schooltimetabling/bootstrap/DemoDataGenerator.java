/*
 * Copyright 2020 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.acme.schooltimetabling.bootstrap;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.acme.schooltimetabling.domain.Lesson;
import org.acme.schooltimetabling.domain.Room;
import org.acme.schooltimetabling.domain.Timeslot;
import org.acme.schooltimetabling.persistence.LessonRepository;
import org.acme.schooltimetabling.persistence.RoomRepository;
import org.acme.schooltimetabling.persistence.TimeslotRepository;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class DemoDataGenerator {

    @ConfigProperty(name = "timeTable.demoData", defaultValue = "SMALL")
    DemoData demoData;

    @Inject
    TimeslotRepository timeslotRepository;
    @Inject
    RoomRepository roomRepository;
    @Inject
    LessonRepository lessonRepository;

    @Transactional
    public void generateDemoData(@Observes StartupEvent startupEvent) {
        generateInitialDataForTenant(1L);
        generateInitialDataForTenant(2L);
        generateInitialDataForTenant(3L);
        generateInitialDataForTenant(4L);
        generateInitialDataForTenant(5L);
    }

    public void generateInitialDataForTenant(Long tenantId) {
        if (demoData == DemoData.NONE) {
            return;
        }

        List<Timeslot> timeslotList = new ArrayList<>(10);
        timeslotList.add(new Timeslot(tenantId, DayOfWeek.MONDAY, LocalTime.of(8, 30), LocalTime.of(9, 30)));
        timeslotList.add(new Timeslot(tenantId, DayOfWeek.MONDAY, LocalTime.of(9, 30), LocalTime.of(10, 30)));
        timeslotList.add(new Timeslot(tenantId, DayOfWeek.MONDAY, LocalTime.of(10, 30), LocalTime.of(11, 30)));
        timeslotList.add(new Timeslot(tenantId, DayOfWeek.MONDAY, LocalTime.of(13, 30), LocalTime.of(14, 30)));
        timeslotList.add(new Timeslot(tenantId, DayOfWeek.MONDAY, LocalTime.of(14, 30), LocalTime.of(15, 30)));

        timeslotList.add(new Timeslot(tenantId, DayOfWeek.TUESDAY, LocalTime.of(8, 30), LocalTime.of(9, 30)));
        timeslotList.add(new Timeslot(tenantId, DayOfWeek.TUESDAY, LocalTime.of(9, 30), LocalTime.of(10, 30)));
        timeslotList.add(new Timeslot(tenantId, DayOfWeek.TUESDAY, LocalTime.of(10, 30), LocalTime.of(11, 30)));
        timeslotList.add(new Timeslot(tenantId, DayOfWeek.TUESDAY, LocalTime.of(13, 30), LocalTime.of(14, 30)));
        timeslotList.add(new Timeslot(tenantId, DayOfWeek.TUESDAY, LocalTime.of(14, 30), LocalTime.of(15, 30)));
        if (demoData == DemoData.LARGE) {
            timeslotList.add(new Timeslot(tenantId, DayOfWeek.WEDNESDAY, LocalTime.of(8, 30), LocalTime.of(9, 30)));
            timeslotList.add(new Timeslot(tenantId, DayOfWeek.WEDNESDAY, LocalTime.of(9, 30), LocalTime.of(10, 30)));
            timeslotList.add(new Timeslot(tenantId, DayOfWeek.WEDNESDAY, LocalTime.of(10, 30), LocalTime.of(11, 30)));
            timeslotList.add(new Timeslot(tenantId, DayOfWeek.WEDNESDAY, LocalTime.of(13, 30), LocalTime.of(14, 30)));
            timeslotList.add(new Timeslot(tenantId, DayOfWeek.WEDNESDAY, LocalTime.of(14, 30), LocalTime.of(15, 30)));
            timeslotList.add(new Timeslot(tenantId, DayOfWeek.THURSDAY, LocalTime.of(8, 30), LocalTime.of(9, 30)));
            timeslotList.add(new Timeslot(tenantId, DayOfWeek.THURSDAY, LocalTime.of(9, 30), LocalTime.of(10, 30)));
            timeslotList.add(new Timeslot(tenantId, DayOfWeek.THURSDAY, LocalTime.of(10, 30), LocalTime.of(11, 30)));
            timeslotList.add(new Timeslot(tenantId, DayOfWeek.THURSDAY, LocalTime.of(13, 30), LocalTime.of(14, 30)));
            timeslotList.add(new Timeslot(tenantId, DayOfWeek.THURSDAY, LocalTime.of(14, 30), LocalTime.of(15, 30)));
            timeslotList.add(new Timeslot(tenantId, DayOfWeek.FRIDAY, LocalTime.of(8, 30), LocalTime.of(9, 30)));
            timeslotList.add(new Timeslot(tenantId, DayOfWeek.FRIDAY, LocalTime.of(9, 30), LocalTime.of(10, 30)));
            timeslotList.add(new Timeslot(tenantId, DayOfWeek.FRIDAY, LocalTime.of(10, 30), LocalTime.of(11, 30)));
            timeslotList.add(new Timeslot(tenantId, DayOfWeek.FRIDAY, LocalTime.of(13, 30), LocalTime.of(14, 30)));
            timeslotList.add(new Timeslot(tenantId, DayOfWeek.FRIDAY, LocalTime.of(14, 30), LocalTime.of(15, 30)));
        }
        timeslotRepository.persist(timeslotList);

        List<Room> roomList = new ArrayList<>(3);
        roomList.add(new Room(tenantId, "Room A"));
        roomList.add(new Room(tenantId, "Room B"));
        roomList.add(new Room(tenantId, "Room C"));
        if (demoData == DemoData.LARGE) {
            roomList.add(new Room(tenantId, "Room D"));
            roomList.add(new Room(tenantId, "Room E"));
            roomList.add(new Room(tenantId, "Room F"));
        }
        roomRepository.persist(roomList);

        List<Lesson> lessonList = new ArrayList<>();
        lessonList.add(new Lesson(tenantId, "Math", "A. Turing", "9th grade"));
        lessonList.add(new Lesson(tenantId, "Math", "A. Turing", "9th grade"));
        lessonList.add(new Lesson(tenantId, "Physics", "M. Curie", "9th grade"));
        lessonList.add(new Lesson(tenantId, "Chemistry", "M. Curie", "9th grade"));
        lessonList.add(new Lesson(tenantId, "Biology", "C. Darwin", "9th grade"));
        lessonList.add(new Lesson(tenantId, "History", "I. Jones", "9th grade"));
        lessonList.add(new Lesson(tenantId, "English", "I. Jones", "9th grade"));
        lessonList.add(new Lesson(tenantId, "English", "I. Jones", "9th grade"));
        lessonList.add(new Lesson(tenantId, "Spanish", "P. Cruz", "9th grade"));
        lessonList.add(new Lesson(tenantId, "Spanish", "P. Cruz", "9th grade"));
        if (demoData == DemoData.LARGE) {
            lessonList.add(new Lesson(tenantId, "Math", "A. Turing", "9th grade"));
            lessonList.add(new Lesson(tenantId, "Math", "A. Turing", "9th grade"));
            lessonList.add(new Lesson(tenantId, "Math", "A. Turing", "9th grade"));
            lessonList.add(new Lesson(tenantId, "ICT", "A. Turing", "9th grade"));
            lessonList.add(new Lesson(tenantId, "Physics", "M. Curie", "9th grade"));
            lessonList.add(new Lesson(tenantId, "Geography", "C. Darwin", "9th grade"));
            lessonList.add(new Lesson(tenantId, "Geology", "C. Darwin", "9th grade"));
            lessonList.add(new Lesson(tenantId, "History", "I. Jones", "9th grade"));
            lessonList.add(new Lesson(tenantId, "English", "I. Jones", "9th grade"));
            lessonList.add(new Lesson(tenantId, "Drama", "I. Jones", "9th grade"));
            lessonList.add(new Lesson(tenantId, "Art", "S. Dali", "9th grade"));
            lessonList.add(new Lesson(tenantId, "Art", "S. Dali", "9th grade"));
            lessonList.add(new Lesson(tenantId, "Physical education", "C. Lewis", "9th grade"));
            lessonList.add(new Lesson(tenantId, "Physical education", "C. Lewis", "9th grade"));
            lessonList.add(new Lesson(tenantId, "Physical education", "C. Lewis", "9th grade"));
        }

        lessonList.add(new Lesson(tenantId, "Math", "A. Turing", "10th grade"));
        lessonList.add(new Lesson(tenantId, "Math", "A. Turing", "10th grade"));
        lessonList.add(new Lesson(tenantId, "Math", "A. Turing", "10th grade"));
        lessonList.add(new Lesson(tenantId, "Physics", "M. Curie", "10th grade"));
        lessonList.add(new Lesson(tenantId, "Chemistry", "M. Curie", "10th grade"));
        lessonList.add(new Lesson(tenantId, "French", "M. Curie", "10th grade"));
        lessonList.add(new Lesson(tenantId, "Geography", "C. Darwin", "10th grade"));
        lessonList.add(new Lesson(tenantId, "History", "I. Jones", "10th grade"));
        lessonList.add(new Lesson(tenantId, "English", "P. Cruz", "10th grade"));
        lessonList.add(new Lesson(tenantId, "Spanish", "P. Cruz", "10th grade"));
        if (demoData == DemoData.LARGE) {
            lessonList.add(new Lesson(tenantId, "Math", "A. Turing", "10th grade"));
            lessonList.add(new Lesson(tenantId, "Math", "A. Turing", "10th grade"));
            lessonList.add(new Lesson(tenantId, "ICT", "A. Turing", "10th grade"));
            lessonList.add(new Lesson(tenantId, "Physics", "M. Curie", "10th grade"));
            lessonList.add(new Lesson(tenantId, "Biology", "C. Darwin", "10th grade"));
            lessonList.add(new Lesson(tenantId, "Geology", "C. Darwin", "10th grade"));
            lessonList.add(new Lesson(tenantId, "History", "I. Jones", "10th grade"));
            lessonList.add(new Lesson(tenantId, "English", "P. Cruz", "10th grade"));
            lessonList.add(new Lesson(tenantId, "English", "P. Cruz", "10th grade"));
            lessonList.add(new Lesson(tenantId, "Drama", "I. Jones", "10th grade"));
            lessonList.add(new Lesson(tenantId, "Art", "S. Dali", "10th grade"));
            lessonList.add(new Lesson(tenantId, "Art", "S. Dali", "10th grade"));
            lessonList.add(new Lesson(tenantId, "Physical education", "C. Lewis", "10th grade"));
            lessonList.add(new Lesson(tenantId, "Physical education", "C. Lewis", "10th grade"));
            lessonList.add(new Lesson(tenantId, "Physical education", "C. Lewis", "10th grade"));

            lessonList.add(new Lesson(tenantId, "Math", "A. Turing", "11th grade"));
            lessonList.add(new Lesson(tenantId, "Math", "A. Turing", "11th grade"));
            lessonList.add(new Lesson(tenantId, "Math", "A. Turing", "11th grade"));
            lessonList.add(new Lesson(tenantId, "Math", "A. Turing", "11th grade"));
            lessonList.add(new Lesson(tenantId, "Math", "A. Turing", "11th grade"));
            lessonList.add(new Lesson(tenantId, "ICT", "A. Turing", "11th grade"));
            lessonList.add(new Lesson(tenantId, "Physics", "M. Curie", "11th grade"));
            lessonList.add(new Lesson(tenantId, "Chemistry", "M. Curie", "11th grade"));
            lessonList.add(new Lesson(tenantId, "French", "M. Curie", "11th grade"));
            lessonList.add(new Lesson(tenantId, "Physics", "M. Curie", "11th grade"));
            lessonList.add(new Lesson(tenantId, "Geography", "C. Darwin", "11th grade"));
            lessonList.add(new Lesson(tenantId, "Biology", "C. Darwin", "11th grade"));
            lessonList.add(new Lesson(tenantId, "Geology", "C. Darwin", "11th grade"));
            lessonList.add(new Lesson(tenantId, "History", "I. Jones", "11th grade"));
            lessonList.add(new Lesson(tenantId, "History", "I. Jones", "11th grade"));
            lessonList.add(new Lesson(tenantId, "English", "P. Cruz", "11th grade"));
            lessonList.add(new Lesson(tenantId, "English", "P. Cruz", "11th grade"));
            lessonList.add(new Lesson(tenantId, "English", "P. Cruz", "11th grade"));
            lessonList.add(new Lesson(tenantId, "Spanish", "P. Cruz", "11th grade"));
            lessonList.add(new Lesson(tenantId, "Drama", "P. Cruz", "11th grade"));
            lessonList.add(new Lesson(tenantId, "Art", "S. Dali", "11th grade"));
            lessonList.add(new Lesson(tenantId, "Art", "S. Dali", "11th grade"));
            lessonList.add(new Lesson(tenantId, "Physical education", "C. Lewis", "11th grade"));
            lessonList.add(new Lesson(tenantId, "Physical education", "C. Lewis", "11th grade"));
            lessonList.add(new Lesson(tenantId, "Physical education", "C. Lewis", "11th grade"));

            lessonList.add(new Lesson(tenantId, "Math", "A. Turing", "12th grade"));
            lessonList.add(new Lesson(tenantId, "Math", "A. Turing", "12th grade"));
            lessonList.add(new Lesson(tenantId, "Math", "A. Turing", "12th grade"));
            lessonList.add(new Lesson(tenantId, "Math", "A. Turing", "12th grade"));
            lessonList.add(new Lesson(tenantId, "Math", "A. Turing", "12th grade"));
            lessonList.add(new Lesson(tenantId, "ICT", "A. Turing", "12th grade"));
            lessonList.add(new Lesson(tenantId, "Physics", "M. Curie", "12th grade"));
            lessonList.add(new Lesson(tenantId, "Chemistry", "M. Curie", "12th grade"));
            lessonList.add(new Lesson(tenantId, "French", "M. Curie", "12th grade"));
            lessonList.add(new Lesson(tenantId, "Physics", "M. Curie", "12th grade"));
            lessonList.add(new Lesson(tenantId, "Geography", "C. Darwin", "12th grade"));
            lessonList.add(new Lesson(tenantId, "Biology", "C. Darwin", "12th grade"));
            lessonList.add(new Lesson(tenantId, "Geology", "C. Darwin", "12th grade"));
            lessonList.add(new Lesson(tenantId, "History", "I. Jones", "12th grade"));
            lessonList.add(new Lesson(tenantId, "History", "I. Jones", "12th grade"));
            lessonList.add(new Lesson(tenantId, "English", "P. Cruz", "12th grade"));
            lessonList.add(new Lesson(tenantId, "English", "P. Cruz", "12th grade"));
            lessonList.add(new Lesson(tenantId, "English", "P. Cruz", "12th grade"));
            lessonList.add(new Lesson(tenantId, "Spanish", "P. Cruz", "12th grade"));
            lessonList.add(new Lesson(tenantId, "Drama", "P. Cruz", "12th grade"));
            lessonList.add(new Lesson(tenantId, "Art", "S. Dali", "12th grade"));
            lessonList.add(new Lesson(tenantId, "Art", "S. Dali", "12th grade"));
            lessonList.add(new Lesson(tenantId, "Physical education", "C. Lewis", "12th grade"));
            lessonList.add(new Lesson(tenantId, "Physical education", "C. Lewis", "12th grade"));
            lessonList.add(new Lesson(tenantId, "Physical education", "C. Lewis", "12th grade"));
        }

        Lesson lesson = lessonList.get(0);
        lesson.setTimeslot(timeslotList.get(0));
        lesson.setRoom(roomList.get(0));

        lessonRepository.persist(lessonList);
    }

    public enum DemoData {
        NONE,
        SMALL,
        LARGE
    }

}
