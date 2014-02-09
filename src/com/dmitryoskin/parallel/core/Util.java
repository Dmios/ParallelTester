package com.dmitryoskin.parallel.core;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dmitry Oskin
 */
public class Util {

    private static final String SET_FILE = "userset.dat";
    private static final String PROPS_FILE = "META-INF/cfg.properties";
    private static final String SMPD_PATH = "smpd-dir";
    private static final String MPIEXEC_PATH = "mpiexec-dir";
    private static final String TESTS_PATH = "tests-dir";
    private static final String RESULTS_PATH = "results-dir";
    private static final String DEPLOY_PATH = "deploy-dir";
	
	private static SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

    private static Properties props;

    private Util() throws Exception {
        super();
    }

    private static String get(String propertyName) {
        if (props == null) {
            props = new Properties();
            try {
                props.load(Util.class.getResourceAsStream(PROPS_FILE));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return (String)props.getProperty(propertyName);
    }

    public static String getSmpdPath() {
        return get(SMPD_PATH);
    }

    public static String getMpiExecPath() {
        return get(MPIEXEC_PATH);
    }

    public static String getTestsPath() {
        return get(TESTS_PATH);
    }

    public static String getResultsPath() {
        return get(RESULTS_PATH);
    }

    public static String getDeployPath() {
        return get(DEPLOY_PATH);
    }

    public static void saveUserSet(UserSet set) throws Exception {
        ObjectOutputStream out =
                        new ObjectOutputStream(new FileOutputStream(SET_FILE));
        out.writeObject(set);
    }

    public static UserSet loadUserSet() throws Exception {
        File set = new File(SET_FILE);
        if (set.exists()) {
            ObjectInputStream in = 
                    new ObjectInputStream(new FileInputStream(set));
            return (UserSet)in.readObject();
        }
        return new UserSet();
    }

    public static UUID getTestUUID(TestType type, String testName) {
        return UUID.randomUUID();
    }
    
    public static Path generateTestDir(TestType type) throws IOException {
        format.applyPattern("dd.MM.yyyy");

        Path dirName = Paths.get(new StringBuilder(getResultsPath())
            .append(File.separator)
            .append(type.toString())
            .append('_')
            .append(format.format(new Date()))
            .toString());

        if (Files.notExists(dirName, LinkOption.NOFOLLOW_LINKS)) {
            Files.createDirectory(dirName);
        }

        return dirName;
    }
	
    public static Path createNewResultFile(TestType type, String testName, UUID testId) throws IOException {                
        Path dirName = generateTestDir(type);        
        System.out.println(dirName);
                
        format.applyPattern("dd.MM.yyyy_hh.mm.ss");
        Path newFile = dirName.resolve(Paths.get(testName + "_" + 
                        format.format(new Date()) + "_" + testId.toString()));
        Files.createFile(newFile);
        return newFile;
    }
	
    public static Path createNewResultEnvironmentFile(TestType type, 
                                                      String host, 
                                                      UUID testId,
                                                      String suffix) throws IOException {
        Path dirName = generateTestDir(type);

        Path hostsInfoDir = dirName.resolve(Paths.get(testId.toString()));
        if (Files.notExists(hostsInfoDir, LinkOption.NOFOLLOW_LINKS)) {
            Files.createDirectory(hostsInfoDir);
        }
        
        
        Path newFile = hostsInfoDir.resolve(Paths.get(host + "-" +  suffix));
        Files.createFile(newFile);
        return newFile;
    }
	
    public static void readFromInfoStream(InputStream in, BufferedWriter out) {
        try (BufferedReader reader = 
                        new BufferedReader(new InputStreamReader(in))) {				

            String line = null;
            while ((line = reader.readLine()) != null) {
                out.append(line).append('\n');
            }
        } catch(IOException ex) {
                System.out.println(ex.getMessage());
        }
    }
}
