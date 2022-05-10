package Labs.Lab11;

import Labs.Lab11.Book;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

public class BookshelfTest {

    Book[] myBooks = { createBook("Wilson", "A. Scott Burg" ),
            createBook("Nixonland", "Rick Perlstein"),
            createBook("Team of Rivals", "Doris Kearns Goodwin" ),
            createBook("What If?", "Randall Munroe" ),
            createBook("The Art of Power", "Jon Meacham") };

    @Test
    public void Bookshelf_instanceCountTest(){
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
    public void Bookshelf_instanceVariablesTest(){
        Bookshelf testBookshelf = new Bookshelf();
        instanceVariablePrivate("size",testBookshelf);
        instanceVariablePrivate("books",testBookshelf);

        instanceVariableStatic("size",testBookshelf);
        instanceVariableStatic("books",testBookshelf);

        instanceVariableCorrectType("size",int.class,testBookshelf);
        instanceVariableCorrectType("books", ArrayList.class,testBookshelf);
    }

    @Test
    public void Bookshelf_defaultConstructorTest() {
        Bookshelf testBookshelf = new Bookshelf();

        testVariable("size",testBookshelf,2,"When checking the value of size we");
        testVariable("books",testBookshelf,new ArrayList<>(),"When checking the value of books we");
    }

    @Test
    public void Bookshelf_parameterizedConstructorTest() {
        Bookshelf testBookshelf = new Bookshelf(5);

        testVariable("size",testBookshelf,5,"When checking the value of size given 5 we");
        testVariable("books",testBookshelf,new ArrayList<>(),"When checking the value of books we");
    }

    @Test
    public void Bookshelf_getSizeTest() {
        Bookshelf testBookshelf = createBookshelf(5,new ArrayList<>());
        assertEquals("With a Bookshelf object who's size instance variable is 5, when calling getSize we",5,testBookshelf.getSize());
    }

    @Test
    public void Bookshelf_getBooksTest() {
        ArrayList<Book> givenBooks = new ArrayList<>(); givenBooks.add(myBooks[0]); givenBooks.add(myBooks[1]);
        ArrayList<Book> expectedBooks = new ArrayList<>(); expectedBooks.add(myBooks[0]); expectedBooks.add(myBooks[1]);
        Bookshelf testBookshelf = createBookshelf(5,givenBooks);
        assertEquals("With a Bookshelf object who's books instance variable which is storing two books, when calling getBooks we",expectedBooks,testBookshelf.getBooks());
    }


    @Test
    public void Bookshelf_addBooksTest() {
        ArrayList<Book> givenBooks = new ArrayList<>(); givenBooks.add(myBooks[0]); givenBooks.add(myBooks[1]);
        ArrayList<Book> expectedBooks = new ArrayList<>(); expectedBooks.add(myBooks[0]); expectedBooks.add(myBooks[1]); expectedBooks.add(myBooks[2]);
        Bookshelf testBookshelf = createBookshelf(5,givenBooks);

        testBookshelf.addBook(myBooks[2]);

        testVariable("books",testBookshelf,expectedBooks,"After calling Bookshelf's addBook method we");
    }

    @Test
    public void Bookshelf_addTooManyBooksTest() {
        ArrayList<Book> givenBooks = new ArrayList<>(); givenBooks.add(myBooks[0]); givenBooks.add(myBooks[1]);
        ArrayList<Book> expectedBooks = new ArrayList<>(); expectedBooks.add(myBooks[0]); expectedBooks.add(myBooks[1]); expectedBooks.add(myBooks[2]);
        Bookshelf testBookshelf = createBookshelf(3,givenBooks);

        testBookshelf.addBook(myBooks[2]);
        testBookshelf.addBook(myBooks[3]);
        testBookshelf.addBook(myBooks[4]);

        testVariable("books",testBookshelf,expectedBooks,"After calling Bookshelf's addBook method we");
    }

    public void Bookshelf_RemoveTwoBooksTest() {
        ArrayList<Book> givenBooks = new ArrayList<>(); givenBooks.add(myBooks[0]); givenBooks.add(myBooks[1]); givenBooks.add(myBooks[2]);
        ArrayList<Book> expectedBooks = new ArrayList<>(); expectedBooks.add(myBooks[2]);
        Bookshelf testBookshelf = createBookshelf(3,givenBooks);


        assertTrue("When checking if the return value of Bookshelf's removeBook method was the Book object at the start of the books ArrayList, we",BookIsEqual(testBookshelf.removeBook(),myBooks[0]));
        assertTrue("When checking if the return value of Bookshelf's removeBook method was the Book object at the start of the books ArrayList, we",BookIsEqual(testBookshelf.removeBook(),myBooks[1]));

        testVariable("books",testBookshelf,expectedBooks,"After calling Bookshelf's removeBook method we");
    }

    @Test
    public void Bookshelf_RemoveTooManyBooksTest() {
        ArrayList<Book> givenBooks = new ArrayList<>(); givenBooks.add(myBooks[0]); givenBooks.add(myBooks[1]); givenBooks.add(myBooks[2]);
        ArrayList<Book> expectedBooks = new ArrayList<>();
        Bookshelf testBookshelf = createBookshelf(3,givenBooks);

        assertTrue("When checking if the return value of Bookshelf's removeBook method was the Book object at the start of the books ArrayList, we",BookIsEqual(testBookshelf.removeBook(),myBooks[0]));
        assertTrue("When checking if the return value of Bookshelf's removeBook method was the Book object at the start of the books ArrayList, we",BookIsEqual(testBookshelf.removeBook(),myBooks[1]));
        assertTrue("When checking if the return value of Bookshelf's removeBook method was the Book object at the start of the books ArrayList, we",BookIsEqual(testBookshelf.removeBook(),myBooks[2]));
        assertNull("When checking if the return value of Bookshelf's removeBook method was the Book object at the start of the books ArrayList, we",testBookshelf.removeBook());
        assertNull("When checking if the return value of Bookshelf's removeBook method was the Book object at the start of the books ArrayList, we",testBookshelf.removeBook());
        assertNull("When checking if the return value of Bookshelf's removeBook method was the Book object at the start of the books ArrayList, we",testBookshelf.removeBook());

        testVariable("books",testBookshelf,expectedBooks,"After calling Bookshelf's removeBook method we");
    }


    @Test
    public void Bookshelf_EmptyBookshelfTest() {
        ArrayList<Book> givenBooks = new ArrayList<>(); givenBooks.add(myBooks[0]); givenBooks.add(myBooks[1]); givenBooks.add(myBooks[2]);
        ArrayList<Book> expectedBooks = new ArrayList<>();
        Bookshelf testBookshelf = createBookshelf(3,givenBooks);

        testBookshelf.emptyBookshelf();

        testVariable("books",testBookshelf,expectedBooks,"After calling Bookshelf's emptyBookshelf method we");
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

    private Bookshelf createBookshelf(int aSize, ArrayList<Book> someBooks){
        Bookshelf testBookshelf = new Bookshelf();
        @SuppressWarnings("rawtypes")
        Class c = testBookshelf.getClass();

        try {
            Field size = c.getDeclaredField("size");
            size.setAccessible(true);
            size.set(testBookshelf, aSize);

            Field flavor = c.getDeclaredField("books");
            flavor.setAccessible(true);
            flavor.set(testBookshelf, someBooks);


        } catch (Exception e) {
            fail(e.toString());
        }

        return testBookshelf;
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
