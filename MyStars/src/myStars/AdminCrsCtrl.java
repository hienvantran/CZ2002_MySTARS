package myStars;

import DB.CourseDB;
import Entities.Course;
import Entities.Index;
import Entities.Lesson;
import Entities.ModeType;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Admin course controller!
 * The Admin course controller extends course controller and
 * The Admin course controller is a controller helps to interface
 * UI with the course.
 * @author Ong Eng Hao
 * @version 1.0
 * @since 2020-11-21
*/
public class AdminCrsCtrl extends CourseCtrl {
    /**
     * A new object of CourseCtrl
     */
    //CourseCtrl courseCtrl = new CourseCtrl();

    /**
     * Adds a course to the database
     * the course should have all the required attributes such as
     * course code, course name, AU, school, type
     * @param course The desired course to add to the database
     */
    public void addCourse(Course course){
        try {
            ArrayList<Course> courseList = getCourseList();
            courseList.add(course);
            setCourseList(courseList);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Remove a course by course code
     * @param courseCode the course code desired to be removed
     */
    public void removeCourse(String courseCode){
        try {
            ArrayList<Course> courseList = getCourseList();
            for(Course course: courseList) {
                if (course.getCourseCode().equals(courseCode)){
                    courseList.remove(course);
                    break;
                }
            }
            setCourseList(courseList);
            System.out.println("You've successfully remove the course: " + courseCode);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes the index for the course
     * @param courseCode the course code
     * @param indexNum the index to remove
     */
    public void removeIndex(String courseCode, int indexNum){

        try{
            ArrayList<Index> indices = getIndexList();
            for(Index index: indices){
                if(index.getIndex()==indexNum&&index.getCourseCode().equals(courseCode)){
                    indices.remove(index);
                    break;
                }
            }
            setIndexList(indices);
            System.out.println("You've successfully remove the index: " + indexNum);

        }catch(IOException e){
            e.printStackTrace();
        }catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update a course's code
     * The old course code should not be the same
     * @param courseCode the old course code
     * @param newCourseCode the new course code
     */
    public void updateCourseCode(String courseCode, String newCourseCode){
        try {
            ArrayList<Course> courses = getCourseList();
            for(Course course:courses){
                if (course.getCourseCode().equals(courseCode))
                    course.setCourseCode(newCourseCode);
            }
            ArrayList<Index> indices = getIndexList();
            for(Index index: indices){
                if(index.getCourseCode().equals(courseCode))
                    index.setCourseCode(newCourseCode);
            }
            setCourseList(courses);
            setIndexList(indices);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update a course's name
     * @param courseCode the course code
     * @param newCourseName the new desired course name
     */
    public void updateCourseName(String courseCode, String newCourseName){
        try {
            ArrayList<Course> courses = getCourseList();
            for(Course course:courses){
                if (course.getCourseCode().equals(courseCode))
                    course.setCourseName(newCourseName);
            }
            setCourseList(courses);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update course's school
     * @param courseCode the course code
     * @param newCourseSchool the new desired course school
     */
    public void updateCourseSchool(String courseCode, String newCourseSchool){
        try {
            ArrayList<Course> courses = getCourseList();
            for(Course course:courses){
                if (course.getCourseCode().equals(courseCode))
                    course.setSchool(newCourseSchool);
            }
            setCourseList(courses);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update the course's AU
     * @param courseCode the course code
     * @param newAU the new desired course AU
     */
    public void updateCourseAU(String courseCode, int newAU){
        try {
            ArrayList<Course> courses = getCourseList();
            for(Course course:courses){
                if (course.getCourseCode().equals(courseCode))
                    course.setCourseAU(newAU);
            }
            setCourseList(courses);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update the course's type
     * @param courseCode the course code
     * @param newType the new desired course type
     */
    public void updateCourseType(String courseCode, String newType){
        try {
            ArrayList<Course> courses = getCourseList();
            for(Course course:courses){
                if (course.getCourseCode().equals(courseCode))
                    course.setCourseType(newType);
            }
            setCourseList(courses);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update the course's index number
     * @param courseCode the course's code
     * @param indexNum the old course name
     * @param newIndexNum the new course name
     */
    public void updateCourseIndex(String courseCode, int indexNum, int newIndexNum){
        try {
            ArrayList<Index> indices = getIndexList();

            for(Index index: indices){
                if(index.getCourseCode().equals(courseCode) && index.getIndex()==indexNum)
                    index.setIndexNumber(newIndexNum);
            }
            setIndexList(indices);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create a new index for a particular course
     * @param courseCode the course code
     * @param indexNum the desired course index to add
     * @param group the desired course group
     * @param totalSlot the desired total slot for the index
     */
    public void createIndex(String courseCode, int indexNum, String group, int totalSlot){
        try{
            ArrayList<Index> indices = getIndexList();
            Index newIndex = new Index(courseCode, indexNum, group, totalSlot, 0);
            indices.add(newIndex);
            setIndexList(indices);
        }catch(IOException e){
            e.printStackTrace();
        }catch (ParseException e) {
            e.printStackTrace();
        }

    }

    /**
     * Update an index's total slot
     * The new total index slot should not be lesser than the old index slot
     * After updating, it will update the vacancy of the course and
     * registers students that are on waiting list on a first come first serve
     * basis
     * @param courseCode the course code
     * @param indexNum the course's index
     * @param totalSlot the desired total slot to be updated
     */
    public void updateIndexTotalSlot(String courseCode, int indexNum, int totalSlot){
        try{
            ArrayList<Index> indices = getIndexList();
            for(Index index: indices){
                if ((index.getCourseCode().equals(courseCode)) && index.getIndex()==indexNum){
                    index.setTotalSlot(totalSlot);
                }
            }
            setIndexList(indices);
            updateRegisteredList(courseCode, indexNum, ModeType.ADMIN);
        }catch(IOException e){
            e.printStackTrace();
        }catch (ParseException e) {
            e.printStackTrace();
        }
    }


    /**
     * Create already existing lectures to index
     * @param courseCode desired
     */
    public void createLessonLecture(String courseCode, int index){
        try {
            ArrayList<Lesson> lessons = getLessonList();

            Lesson newLesson = new Lesson("Unknown", 1,"Unknown", "Unknown", "Unknown", "Unknown");
            // add the existing lectures
            for (Lesson lesson: lessons){
                if (lesson.getCrsCode().equals(courseCode)&&lesson.getLessonType().equals("LEC")){
                    newLesson.setindexNo(index);
                    newLesson.setCrsCode(lesson.getCrsCode());
                    newLesson.setLessonDay(lesson.getLessonDay());
                    newLesson.setLessonTime(lesson.getLessonTime());
                    newLesson.setLessonType("LEC");
                    newLesson.setLessonVenue(lesson.getLessonVenue());
                }
            }
            lessons.add(newLesson);

            setLessonList(lessons);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
    /**
     * Checks the existence lesson of a course code
     * @return true if lessson exists, else false
     */
    public boolean isExistingLesson(String courseCode){
        try{
            ArrayList<Lesson> lessons = getLessonList();
            for(Lesson lesson: lessons){
                if(lesson.getCrsCode().equals(courseCode))
                    return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * Get the lesson type of the course
     * @param courseCode desired course code to view the lesson
     */
    public int getLessonType(String courseCode){
        try {
            ArrayList<Course> courses = getCourseList();
            for(Course course: courses){
                if (course.getCourseCode().equals(courseCode))
                    return course.getLessonType();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Creates lesson
     * @param courseCode the course code for the lesson
     * @param index the index for the lesson
     * @param preferredDay the preferred day for the lesson
     * @param preferredHours the preferred time for the lesson
     * @param lessonType the preferred lesson type for the lesson (e.g. LAB, LEC, TUT)
     * @param lessonLocation the preferred lesson location for the lesson
     */
    public void createLesson(String courseCode, int index, String preferredDay, String preferredHours, String lessonType, String lessonLocation){
        //get the existing and create automatically
        try {
            ArrayList<Lesson> lessons = getLessonList();
            Lesson lesson = new Lesson(courseCode, index, lessonType, preferredDay, preferredHours, lessonLocation);
            lessons.add(lesson);
            setLessonList(lessons);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}
