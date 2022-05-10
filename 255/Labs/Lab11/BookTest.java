package Labs.Lab11;

import Labs.Lab11.Book;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

public class BookTest {

    Book[] myBooks = { createBook("Wilson", "A. Scott Burg" ),
            createBook("Nixonland", "Rick Perlstein"),
            createBook("Team of Rivals", "Doris Kearns Goodwin" ),
            createBook("What If?", "Randall Munroe" ),
            createBook("The Art of Power", "Jon Meacham") };


    @Test
    public void Book_instanceCountTest(){
        Book testBook = new Book();
        @SuppressWarnings("rawtypes")
        Class c = testBook.getClass();
        try {
            assertEquals(
                    "You must only have the instance variables specified. When looking at the number of instance variables we",
                    2, c.getDeclaredFields().length);
        }
        catch (Exception e) {
            fail("Something weird went wrong");
        }
    }

    @Test
    public void Book_instanceVariablesTest(){
        Book testBook = new Book();
        instanceVariablePrivate("author",testBook);
        instanceVariablePrivate("title",testBook);

        instanceVariableStatic("author",testBook);
        instanceVariableStatic("title",testBook);

        instanceVariableCorrectType("author",String.class,testBook);
        instanceVariableCorrectType("title",String.class,testBook);
    }

    @Test
    public void Book_defaultConstructorTest() {
        Book testBook = new Book();

        testVariable("author",testBook,null,"When checking the value of author we");
        testVariable("title",testBook, "CMSC256/Test","When checking the value of title we");
    }

    @Test
    public void Book_parameterizedConstructorTest() {
        Book testBook = new Book("My Book","Author");

        testVariable("author",testBook,"Author","When checking the value of author given \"Author\" we");
        testVariable("title",testBook,"My Book","When checking the value of title given \"My Book\" we");
    }

    @Test
    public void Book_getTitleTest() {
        Book testBook = createBook("The Federilist Papers","Alexander Hamilton");
        assertEquals("With a Book object who's title instance variable is \"The Federilist Papers\", when calling getSize we","The Federilist Papers",testBook.getTitle());
    }

    @Test
    public void Book_setTitleTest() {
        Book testBook = createBook("The Federilist Papers","Alexander Hamilton");
        testBook.setTitle("The Reynolds Pamphlet");
        testVariable("title",testBook,"The Reynolds Pamphlet","After calling book's setTitle method with an argument of \"The Reynolds Pamphlet\", for the value of title we");
    }

    @Test
    public void Book_getAuthorTest() {
        Book testBook = createBook("The Federilist Papers","Alexander Hamilton");
        assertEquals("With a Book object who's author instance variable is \"Alexander Hamilton\", when calling getAuthor we","Alexander Hamilton",testBook.getAuthor());
    }

    @Test
    public void Book_setAuthorTest() {
        Book testBook = createBook("Graphical User Interfaces","Xerox Alto");
        testBook.setAuthor("Steve Jobs");
        testVariable("author",testBook,"Steve Jobs","After calling book's setTitle method with an argument of \"Steve Jobs\", for the value of author we");
    }

    @Test
    public void Book_ToString() {
        assertEquals("\"Nixonland\" by Rick Perlstein", myBooks[1].toString());
    }

    private Book createBook(String aTitle, String anAuthor){
        Book testBook = new Book();
        @SuppressWarnings("rawtypes")
        Class c = testBook.getClass();

        try {
            Field size = c.getDeclaredField("author");
            size.setAccessible(true);
            size.set(testBook, anAuthor);

            Field flavor = c.getDeclaredField("title");
            flavor.setAccessible(true);
            flavor.set(testBook, aTitle);

        } catch (Exception e) {
            fail(e.toString());
        }

        return testBook;
    }

    private void instanceVariablePrivate(String aField, Object testObject) {
        @SuppressWarnings("rawtypes")
        Class c = testObject.getClass();
        try {
            c.getDeclaredField(aField);

            assertTrue("You must make your instance variables private.", Modifier.isPrivate(c.getDeclaredField(aField).getModifiers()));

        } catch (NoSuchFieldException e) {
            fail("Could not find the " + e.getLocalizedMessage() + " instance variable");
        } catch (Exception e) {
            fail("Something weird went wrong");
        }
    }

    private void instanceVariableStatic(String aField, Object testObject) {
        @SuppressWarnings("rawtypes")
        Class c = testObject.getClass();
        try {
            c.getDeclaredField(aField);

            assertEquals("Your instance variables must NOT be static.", false,
                    Modifier.isStatic(c.getDeclaredField(aField).getModifiers()));

        } catch (NoSuchFieldException e) {
            fail("Could not find the " + e.getLocalizedMessage() + " instance variable");
        } catch (Exception e) {
            fail("Something weird went wrong");
        }
    }

    private void instanceVariableCorrectType(String aField, Class<?> aClass,  Object testObject) {
        @SuppressWarnings("rawtypes")
        Class c = testObject.getClass();
        try {
            c.getDeclaredField(aField);

            assertEquals("You must make the speed instance variable of type"+ aClass.toString() +".", aClass, c.getDeclaredField(aField).getType());

        } catch (NoSuchFieldException e) {
            fail("Could not find the " + e.getLocalizedMessage() + " instance variable");
        } catch (Exception e) {
            fail("Something weird went wrong");
        }
    }

    private void testVariable(String aField, Object testObject, Object expected, String message){
        @SuppressWarnings("rawtypes")
        Class c = testObject.getClass();
        try {
            Field field = c.getDeclaredField(aField);
            field.setAccessible(true);
            Object fieldValue = field.get(testObject);

            if(expected == null){
                assertNull(message,fieldValue);
            }
            //If class is a double we have a special Junit assert to run
            else if(expected.getClass().equals(Double.class)){
                double doubleFieldValue = (double) fieldValue;
                double doubleExpected = (double) expected;
                assertEquals(message, doubleExpected, doubleFieldValue, .01);
            }
            //Array of some kind yay
            else if(expected.getClass().isArray()){

            }
            else if(expected.getClass().equals(ArrayList.class)){
                testBookArray(message,(ArrayList) expected, (ArrayList) fieldValue);
            }
            else{
                assertEquals(message, expected, fieldValue);
            }

        }
        catch (Exception e) {
            fail(e.toString());
        }
    }

    private void testBookArray(String message, ArrayList expected, ArrayList actual){
        assertEquals(message + " looked at the size and ",expected.size(),actual.size());

        for(int i = 0; i < expected.size(); i++) {
            if (!BookIsEqual(expected.get(i), actual.get(i))) {
                assertEquals(message, expected, actual);
            }
        }
    }

    private boolean BookIsEqual(Object o1, Object o2){
        @SuppressWarnings("rawtypes")
        Class c = o1.getClass();
        try {
            Field authorFieldo1 = c.getDeclaredField("author");
            authorFieldo1.setAccessible(true);
            Object authoro1 = authorFieldo1.get(o1);

            Field authorFieldo2 = c.getDeclaredField("author");
            authorFieldo2.setAccessible(true);
            Object authoro2 = authorFieldo2.get(o2);

            Field titleFieldo1 = c.getDeclaredField("title");
            titleFieldo1.setAccessible(true);
            Object titleo1 = titleFieldo1.get(o1);

            Field titleFieldo2 = c.getDeclaredField("title");
            titleFieldo2.setAccessible(true);
            Object titleo2 = titleFieldo2.get(o2);

            return authoro1.equals(authoro2) && titleo1.equals(titleo2);

        } catch (NoSuchFieldException e) {
            fail("Could not find the " + e.getLocalizedMessage() + " instance variable");
        } catch (Exception e) {
            fail("Something weird went wrong");
        }

        return false;
    }

}
