package ch.heigvd.res.labs.roulette.net.client;

import ch.heigvd.res.labs.roulette.data.EmptyStoreException;
import ch.heigvd.res.labs.roulette.data.JsonObjectMapper;
import ch.heigvd.res.labs.roulette.net.protocol.RouletteV1Protocol;
import ch.heigvd.res.labs.roulette.data.Student;
import ch.heigvd.res.labs.roulette.net.protocol.InfoCommandResponse;
import ch.heigvd.res.labs.roulette.net.protocol.RandomCommandResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class implements the client side of the protocol specification (version 1).
 * 
 * @author Olivier Liechti
 */
public class RouletteV1ClientImpl implements IRouletteV1Client {

  private static final Logger LOG = Logger.getLogger(RouletteV1ClientImpl.class.getName());
  boolean connection = false;
  ArrayList<Student> studentList = new ArrayList<Student>();


  @Override
  public void connect(String server, int port) throws IOException {
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    connection = true;
  }

  @Override
  public void disconnect() throws IOException {
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    connection = false;
  }

  @Override
  public boolean isConnected() {
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    return connection;
  }

  @Override
  public void loadStudent(String fullname) throws IOException {
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    Student newStudent = new Student();
    newStudent.setFullname(fullname);
    studentList.add(newStudent);
  }

  @Override
  public void loadStudents(List<Student> students) throws IOException {
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    studentList.add((Student) students);
  }

  @Override
  public Student pickRandomStudent() throws EmptyStoreException, IOException {
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    if(studentList.size() == 0){
      throw new EmptyStoreException();
    }

    Random rnd = new Random();
    Student randStudent = null;
    int randNumber = rnd.nextInt(studentList.size() - 1);
    Iterator it = studentList.iterator();
    for( int i = 0; i < randNumber; i++){
      randStudent = (Student) it.next();
    }

    return randStudent;

  }

  @Override
  public int getNumberOfStudents() throws IOException {
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    return studentList.size();
  }

  @Override
  public String getProtocolVersion() throws IOException {
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    return "1.0";
  }



}
