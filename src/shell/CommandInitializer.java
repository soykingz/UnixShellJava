// **********************************************************
// Assignment2:

// Student1:
// UTORID user_name: liangho2
// UT Student #:1002408085
// Author: Hong Xuan David Liang
//
// Student2:
// UTORID user_name:jinze1
// UT Student #:1003155863
// Author: Ze Jin
//
// Student3:
// UTORID user_name: seyedayd
// UT Student #: 1002471201
// Author: Aydin Baradaran-Seyed
//
// Student4:
// UTORID user_name: vezocluc
// UT Student #: 1002403749
// Author: Luca Vezoc
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************

package shell;

import command.Command;
import command.UrlDownloader;
import filesystem.FileSystem;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class which validates and constructs Commands for use.
 * 
 * @author liangho2
 */
public class CommandInitializer {

  // Dict to store commands.
  private final Map<String, List<Object>> commandDict = new HashMap<>();
  // Another dict to store command parameters.
  private final Map<String, Object[]> commandArgs = new HashMap<>();
  // Singleton instance, since only one dict is needed.
  private static CommandInitializer singleInstance = null;

  /**
   * Populates HashMaps with commands and their corresponding class and
   * parameter information.
   * 
   * @param fs reference to FileSystem instance
   * @param io reference to IoHandler instance
   * @param h reference to UserInputHistory instance
   * @param ex reference to CommandExecutor instance
   * @param ud reference to UrlDownloader instance
   */
  private CommandInitializer(FileSystem fs, IoHandler io, UserInputHistory h,
      CommandExecutor ex, UrlDownloader ud) {
    // Populate commandDict with relevant class information. Populate
    // commandArgs with parameter information.
    // Note that the extra dict is necessary to use reflection for commands
    // such as history and man, which have a different number and type of
    // constructor arguments. Also note that line count is simply due to code
    // wrapping constraints.
    commandDict.put("mkdir",
        Arrays.asList("command.Mkdir", FileSystem.class, IoHandler.class));
    commandArgs.put("mkdir", Arrays.asList(fs, io).toArray());

    commandDict.put("cd",
        Arrays.asList("command.Cd", FileSystem.class, IoHandler.class));
    commandArgs.put("cd", Arrays.asList(fs, io).toArray());

    commandDict.put("ls",
        Arrays.asList("command.Ls", FileSystem.class, IoHandler.class));
    commandArgs.put("ls", Arrays.asList(fs, io).toArray());

    commandDict.put("pwd",
        Arrays.asList("command.Pwd", FileSystem.class, IoHandler.class));
    commandArgs.put("pwd", Arrays.asList(fs, io).toArray());

    // commandDict.put("pushd", new Pushd(fileSystem, ioHandler));
    // commandDict.put("popd", new Popd(fileSystem, ioHandler));

    commandDict.put("history", Arrays.asList("command.History", IoHandler.class,
        UserInputHistory.class));
    commandArgs.put("history", Arrays.asList(io, h).toArray());

    commandDict.put("cat",
        Arrays.asList("command.Cat", FileSystem.class, IoHandler.class));
    commandArgs.put("cat", Arrays.asList(fs, io).toArray());

    commandDict.put("echo", Arrays.asList("command.Echo", IoHandler.class));
    commandArgs.put("echo", Arrays.asList(io).toArray());

    commandDict.put("man", Arrays.asList("command.Man", IoHandler.class));
    commandArgs.put("man", Arrays.asList(io).toArray());

    // commandDict.put("find", new Find(fileSystem, ioHandler));

    commandDict.put("tree",
        Arrays.asList("command.Tree", FileSystem.class, IoHandler.class));
    commandArgs.put("tree", Arrays.asList(fs, io).toArray());

    commandDict.put("!",
        Arrays.asList("command.HistoryRecallExecute", IoHandler.class,
            UserInputHistory.class, CommandInitializer.class,
            CommandExecutor.class));
    commandArgs.put("!", Arrays.asList(io, h, this, ex).toArray());

    commandDict.put("curl", Arrays.asList("command.Curl", FileSystem.class,
        IoHandler.class, UrlDownloader.class));
    commandArgs.put("curl", Arrays.asList(fs, io, ud).toArray());
  }

  /**
   * Creates a singleton instance of CommandInitializer (factory method).
   * 
   * @param fs reference to FileSystem instance
   * @param io reference to IoHandler instance
   * @param h reference to UserInputHistory instance
   * @param ex reference to CommandExecutor instance
   * @param ud reference to UrlDownloader instance
   * @return singleInstance reference to a singleton CommandInitializer instance
   */
  public static CommandInitializer createInstance(FileSystem fs, IoHandler io,
      UserInputHistory h, CommandExecutor ex, UrlDownloader ud) {
    // Only create CommandInitializer if one does not already exist.
    if (singleInstance == null) {
      singleInstance = new CommandInitializer(fs, io, h, ex, ud);
    }
    return singleInstance;
  }

  /**
   * Returns a Command after checking if a given string represents a valid
   * command and instantiating an instance of said command if so.
   * 
   * @param str input command string
   * @return reference to instantiated command
   * @throws CommandInstantiationException thrown if an error occurs during
   *         reflection (trying to access command constructors)
   * @throws InvalidCommandException thrown if input str command is invalid
   */
  public Command validate(String str)
      throws CommandInstantiationException, InvalidCommandException {
    // Check if str is in the hashmap; if it is, get its command's string
    // representation and its parameter type information.
    if (commandDict.containsKey(str)) {

      // String to store command string representation. Note that casts are
      // necessary as the information is stored as an Object.
      String commandClassString = (String) commandDict.get(str).get(0);
      // Variable to store number of constructor parameters of a command.
      int numOfParams = commandDict.get(str).size() - 1;
      // Array to store types of parameters for a command.
      Class<?>[] commandParamTypes = new Class[numOfParams];

      // Populate commandParamTypes with however many parameters are present,
      // ignoring the string representation of the command class itself.
      for (int i = 0; i < numOfParams; i++) {
        commandParamTypes[i] =
            (Class<?>) commandDict.get(str).subList(1, numOfParams + 1).get(i);
      }
      try {
        // Use reflection to get the Class object of the command.
        Class<?> classObj = Class.forName(commandClassString);
        // Get the constructor of the command.
        Constructor<?> commandConstructor =
            classObj.getDeclaredConstructor(commandParamTypes);
        // Make sure the constructor is accessible (in case any particular
        // commands are using a singleton design).
        commandConstructor.setAccessible(true);
        // Create a new instance of the command using reflection and passing in
        // the constructor parameters stored in commandArgs.
        Command commandInstance =
            (Command) commandConstructor.newInstance(commandArgs.get(str));
        return commandInstance;
      } catch (ClassNotFoundException | NoSuchMethodException
          | SecurityException | InstantiationException | IllegalAccessException
          | IllegalArgumentException | InvocationTargetException e) {
        // Throw an instantiation exception if an error occurs.
        throw new CommandInstantiationException();
      }

    } else {
      // Command must be invalid, so throw relevant exception.
      throw new InvalidCommandException();
    }
  }

  /**
   * Returns a string representing the instance of CommandInitializer.
   * 
   * @return string representing the instance of CommandInitializer
   */
  public String toString() {
    return ("singleton instance of CommandInitializer");
  }

}
