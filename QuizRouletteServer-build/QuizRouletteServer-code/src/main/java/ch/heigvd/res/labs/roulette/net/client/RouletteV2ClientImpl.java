package ch.heigvd.res.labs.roulette.net.client;

import ch.heigvd.res.labs.roulette.data.JsonObjectMapper;
import ch.heigvd.res.labs.roulette.data.Student;
import ch.heigvd.res.labs.roulette.data.StudentsList;
import ch.heigvd.res.labs.roulette.net.protocol.RouletteV2Protocol;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the client side of the protocol specification (version 2).
 *
 * @author Olivier Liechti
 */
public class RouletteV2ClientImpl extends RouletteV1ClientImpl implements IRouletteV2Client {

  @Override
  public void clearDataStore() throws IOException {
    studentList.clear();
  }

  @Override
  public List<Student> listStudents() throws IOException {
    return new ArrayList<>(studentList);
  }

  @Override
  public String getProtocolVersion() throws IOException {
    return "2.0";
  }
  
}
