package ch.heigvd.res.labs.roulette.net.server;

import ch.heigvd.res.labs.roulette.data.*;
import ch.heigvd.res.labs.roulette.net.protocol.*;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class implements the Roulette protocol (version 2).
 *
 * @author Olivier Liechti
 */
public class RouletteV2ClientHandler implements IClientHandler {
  final static Logger LOG = Logger.getLogger(RouletteV2ClientHandler.class.getName());

  private final IStudentsStore store;

  public RouletteV2ClientHandler(IStudentsStore store) {
    this.store = store;
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of gen
  }

  @Override
  public void handleClientConnection(InputStream is, OutputStream os) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    PrintWriter writer = new PrintWriter(new OutputStreamWriter(os));

    writer.println("Hello. Online HELP is available. Will you find it?");
    writer.flush();

    String command;
    boolean done = false;
    int commmandCounter = 0;
    while (!done && ((command = reader.readLine()) != null)) {
      LOG.log(Level.INFO, "COMMAND: {0}", command);
      commmandCounter++;
      switch (command.toUpperCase()) {
        case RouletteV2Protocol.CMD_RANDOM:
          RandomCommandResponse rcResponse = new RandomCommandResponse();
          try {
            rcResponse.setFullname(store.pickRandomStudent().getFullname());
          } catch (EmptyStoreException ex) {
            rcResponse.setError("There is no student, you cannot pick a random one");
          }
          writer.println(JsonObjectMapper.toJson(rcResponse));
          writer.flush();
          break;
        case RouletteV2Protocol.CMD_HELP:
          writer.println("Commands: " + Arrays.toString(RouletteV2Protocol.SUPPORTED_COMMANDS));
          break;
        case RouletteV2Protocol.CMD_INFO:
          InfoCommandResponse response = new InfoCommandResponse(RouletteV2Protocol.VERSION, store.getNumberOfStudents());
          writer.println(JsonObjectMapper.toJson(response));
          writer.flush();
          break;
        case RouletteV2Protocol.CMD_LOAD:
            writer.println(RouletteV2Protocol.RESPONSE_LOAD_START);
            writer.flush();
            int nbData = store.importData(reader);

          //writer.println(RouletteV2Protocol.RESPONSE_LOAD_DONE);
          InfoCommandLoad responseLoad = new InfoCommandLoad("success", nbData);
          writer.println(JsonObjectMapper.toJson(responseLoad));
          writer.flush();
          break;
        case RouletteV2Protocol.CMD_CLEAR:
          store.clear();
          break;
        case RouletteV2Protocol.CMD_LIST:
          StudentsList studentList = store.listStudents();
          if(studentList.size() == 0){
            writer.println("There is no student");
          }
          else{
            writer.println(JsonObjectMapper.toJson(studentList));
            writer.flush();
          }
          break;
        case RouletteV2Protocol.CMD_BYE:
          done = true;
          InfoCommandBye responseBye = new InfoCommandBye("success", commmandCounter);
          writer.println(JsonObjectMapper.toJson(responseBye));
          writer.flush();
          break;
        default:
          writer.println("Huh? please use HELP if you don't know what commands are available.");
          writer.flush();
          break;
      }
      writer.flush();
    }
  }

}
