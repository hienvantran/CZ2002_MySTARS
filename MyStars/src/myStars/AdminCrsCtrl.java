package myStars;

import DB.CourseDB;
import Entities.Course;
import Entities.Index;
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
 * <p>
 * <b>Note:</b> Giving proper comments in your program makes it more
 * user friendly and it is assumed as a high quality code.
 * </p>
 * @author Ong Eng Hao
 * @version 1.0
 * @since 2020-11-21
*/
public class AdminCrsCtrl extends CourseCtrl {
    /**
     * A new object of CourseCtrl
     */
    CourseCtrl courseCtrl = new CourseCtrl();

    /**
     * Adds a course to the database
     * the course should have all the required attributes such as
     * course code, course name, AU, school, type
     * @param course The desired course to add to the database
     */
    public void addCourse(Course course){
        try {
            ArrayList<Course> courseList = courseCtrl.getCourseList();
            courseList.add(course);
            courseCtrl.setCourseList(courseList);
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
            ArrayList<Course> courseList = courseCtrl.getCourseList();
            for(Course course: courseList) {
                if (course.getCourseCode().equals(courseCode)){
                    courseList.remove(course);
                    break;
                }
            }
            courseCtrl.setCourseList(courseList);
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
            ArrayList<Index> indices = courseCtrl.getIndexList();
            for(Index index: indices){
                if(index.getIndex()==indexNum&&index.getCourseCode().equals(courseCode)){
                    indices.remove(index);
                    break;
                }
            }
            courseCtrl.setIndexList(indices);
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
            ArrayList<Course> courses = courseCtrl.getCourseList();
            for(Course course:courses){
                if (course.getCourseCode().equals(courseCode))
                    course.setCourseCode(newCourseCode);
            }
            ArrayList<Index> indices = courseCtrl.getIndexList();
            for(Index index: indices){
                if(index.getCourseCode().equals(courseCode))
                    index.setCourseCode(newCourseCode);
            }
            courseCtrl.setCourseList(courses);
            courseCtrl.setIndexList(indices);
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
            ArrayList<Course> courses = courseCtrl.getCourseList();
            for(Course course:courses){
                if (course.getCourseCode().equals(courseCode))
                    course.setCourseName(newCourseName);
            }
            courseCtrl.setCourseList(courses);
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
            ArrayList<Course> courses = courseCtrl.getCourseList();
            for(Course course:courses){
                if (course.getCourseCode().equals(courseCode))
                    course.setSchool(newCourseSchool);
            }
            courseCtrl.setCourseList(courses);
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
            ArrayList<Course> courses = courseCtrl.getCourseList();
            for(Course course:courses){
                if (course.getCourseCode().equals(courseCode))
                    course.setCourseAU(newAU);
            }
            courseCtrl.setCourseList(courses);
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
            ArrayList<Course> courses = courseCtrl.getCourseList();
            for(Course course:courses){
                if (course.getCourseCode().equals(courseCode))
                    course.setCourseType(newType);
            }
            courseCtrl.setCourseList(courses);
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
            ArrayList<Index> indices = courseCtrl.getIndexList();

            for(Index index: indices){
                if(index.getCourseCode().equals(courseCode) && index.getIndex()==indexNum)
                    index.setIndexNumber(newIndexNum);
            }
            courseCtrl.setIndexList(indices);
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
            ArrayList<Index> indices = courseCtrl.getIndexList();
            Index newIndex = new Index(courseCode, indexNum, group, totalSlot, 0);
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
            ArrayList<Index> indices = courseCtrl.getIndexList();
            for(Index index: indices){
                if ((index.getCourseCode().equals(courseCode)) && index.getIndex()==indexNum){
                    index.setTotalSlot(totalSlot);
                }
            }
            courseCtrl.setIndexList(indices);
            updateRegisteredList(courseCode, indexNum, ModeType.ADMIN);
        }catch(IOException e){
            e.printStackTrace();
        }catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
