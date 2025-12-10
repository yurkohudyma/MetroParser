package ua.hudyma;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static ua.hudyma.ErrorCode.CRITICAL;
import static ua.hudyma.ErrorCode.NO_ERROR;
import static ua.hudyma.Validator.validate;
import static ua.hudyma.Validator.validateRawData;

public class Parser {
    private static final String FILE_URL = "res//metro.txt";
    private static final int METRO2_FILE_FORMAT_LENGTH = 426;
    private static final List<ErrorLog> errorList = new ArrayList<>();

    public static void main(String[] args) throws
            IOException, InvocationTargetException,
            InstantiationException, IllegalAccessException {
        var headerMap = readRawDataAndCreateHeaderMap();
        var headerRecordInstance = convertMapIntoRecord(headerMap);
        reportIssues(headerRecordInstance);
    }

    private static void reportIssues(DataHeader headerRecordInstance) {
        System.out.println(headerRecordInstance);
        System.out.println();
        errorList.forEach(System.out::println);
        System.out.println();
        System.out.println("ERRORS found: " + errorList.size());
        System.out.println();
        errorList.stream().filter(error -> error.errorCode() == CRITICAL).forEach(System.out::println);
    }

    private static Map<String, String> readRawDataAndCreateHeaderMap() throws IOException {
        var reader = new ThreadSafeReader(
                new BufferedReader(new FileReader(FILE_URL), 128 * 1024));
        var rawData = reader.readLine();
        var rawDataerrorCode = validateRawData(rawData);
        if (rawDataerrorCode != NO_ERROR){
            errorList.add(new ErrorLog(
                    "rawDataFile",
                    rawDataerrorCode,
                    "NA",
                    String.format("File length = %d, while required = %d", rawData.length(), METRO2_FILE_FORMAT_LENGTH

            )));
        }
        var headerEnumSet = EnumSet.allOf(Header.class);
        var map = new LinkedHashMap<String, String>();
        for (Header entry : headerEnumSet) {
            var rawDataString = rawData.substring(
                    entry.getStartIndex() - 1,
                    entry.getStartIndex() + entry.getLength() - 1);
            var fieldName = entry.getFieldName();
            map.put(fieldName, rawDataString);
            var errorCode = validate(fieldName, rawDataString);
            if (!errorCode.equals(NO_ERROR)) {
                errorList.add(new ErrorLog(
                        fieldName,
                        errorCode,
                        compileLinePosition(entry),
                        rawDataString
                ));

            }
        }
        return map;
    }

    private static String compileLinePosition(Header entry) {
        return String.format("[%d : %d]", entry.getStartIndex(), entry.getEndIndex());
    }

    private static DataHeader convertMapIntoRecord(
            Map<String, String> headerMap) throws InvocationTargetException,
            InstantiationException, IllegalAccessException {
        var clazz = DataHeader.class;
        var components = clazz.getRecordComponents();
        var args = new Object[components.length];
        for (int i = 0; i < components.length; i++) {
            String fieldName = components[i].getName();
            args[i] = headerMap.get(fieldName);
        }
        return (DataHeader) clazz.getDeclaredConstructors()[0].newInstance(args);
    }
}