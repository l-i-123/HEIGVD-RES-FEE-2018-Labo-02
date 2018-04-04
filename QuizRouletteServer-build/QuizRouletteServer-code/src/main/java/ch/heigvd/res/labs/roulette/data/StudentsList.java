package ch.heigvd.res.labs.roulette.data;

import javax.swing.text.html.HTMLDocument;
import java.util.*;

/**
 * This class is used to support serialization/deserialization of the protocol
 * messages. The JsonObjectMapper can convert instances of this class into a
 * JSON representation and vice-versa.
 * 
 * @author Olivier Liechti
 */
public class StudentsList {
  
  private final List<Student> students = new ArrayList<>();

  public List<Student> getStudents() {
    ArrayList<Student> list = new ArrayList<>();
    list.addAll(students);
    return list;
  }
  
  public void setStudents(List<Student> list) {
    students.clear();
    students.addAll(list);
  }
  
  public void addAll(List<Student> fromList) {
    setStudents(fromList);
  }

  public void clear(){
    students.clear();
  }

  public void add(Student student){
    students.add(student);
  }

  public boolean isEmpty(){
    if(students.size() == 0){
      return true;
    }
    return false;
  }

  public int size(){
    return students.size();
  }

  public Student get(int index){
    Iterator<Student> it = students.iterator();
    Student valRetour;
    for(int i = 0; i < index; i++){
      it.next();
    }
    valRetour = it.next();
    return valRetour;
  }

}
