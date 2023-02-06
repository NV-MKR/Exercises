import java.util.ArrayList;
import java.util.Scanner;

public class MainOperations {

    public static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {

        printIntro();
        printAvailableSystems();

        int sourceSystem = 0;
        sourceSystem = getEnteredSystem(sourceSystem);

        int targetSystem = 1;
        targetSystem = getEnteredSystem(targetSystem);

        printUserDecision(sourceSystem, targetSystem);

        String userNumber = getEnteredNumberFromUser();

        ConverterHandler converterHandler = new ConverterHandler();
        String result = converterHandler.getResultFromConverter(userNumber, sourceSystem, targetSystem);
        printResult(result);
    }

    public static void printIntro(){

        String intro = "";
        intro += "Willkommen beim Zahlenkonverter\n";
        intro += "Folgende Zahlentypen können ineinander konvertiert werden:" ;
        System.out.println(intro);
    }

    public static void printAvailableSystems(){

        String systems = "";
        systems += "2:  " + getNumberType(2) + "\n";
        systems += "8:  " + getNumberType(8) +  "\n";
        systems += "10: " + getNumberType(10) +  "\n";
        systems += "16: " + getNumberType(16);
        System.out.println(systems);
    }

    protected static String getNumberType(int system)
    {
        switch(system)
        {
            case 2: return "Binärzahl";
            case 8: return "Oktalzahl";
            case 10: return "Dezimalzahl";
            case 16: return "Hexadezimalzahl";
        }
        return "Der eingegebene Zahlentyp wird nicht unterstützt";
    }

    public static int getEnteredSystem(int systemType)
    {
        String type = "";
        if(systemType == 0)
        {
            type = "Quell";
        }
        else if(systemType == 1)
        {
            type = "Ziel";
        }
        System.out.print("Bitte geben sie die entsprechende Zahl für Ihr " + type + "-Zahlensystem ein: ");
        return keyboard.nextInt();
    }

    public static void printUserDecision(int sourceSystem, int targetSystem){

        String source = getNumberType(sourceSystem);
        String target = getNumberType(targetSystem);
        String decision = "Sie möchten eine " + source + " in eine " + target + " konvertieren.";
        System.out.println(decision);
    }

    public static String getEnteredNumberFromUser()
    {
        String userNumberChoice = "Bitte geben Sie eine ganze Zahl ein, die Sie konvertieren möchten: ";
        System.out.print(userNumberChoice);
        return keyboard.next();
    }

    public static void printResult(String result)
    {
        String results = "Ergebnis der Konvertierung: " + result + "\n";
        results += "Danke, dass Sie diesen Konverter genutzt haben.";
        System.out.println(results);
    }
}

class ConverterHandler {

    public static String getResultFromConverter(String userNumber, int sourceSystem, int targetSystem)
    {
        var SourceConverter = ConverterHandler.getMatchingConverter(sourceSystem);
        int decimalNumber = SourceConverter.getConvertedToDecimal(userNumber);

        var TargetConverter = ConverterHandler.getMatchingConverter(targetSystem);
        String result = TargetConverter.getConvertedFromDecimal(decimalNumber);

        return result;
    }

    private static Converter getMatchingConverter(int system) {

        switch (system) {
            case 2: return new Binary();
            case 8:  return new Octal();
            case 10: return new Decimal();
            case 16: return new Hexadecimal();
            default: System.out.print("Das eingegebene Zahlensystem wird nicht unterstützt."); return null;
        }
    }
}

class Converter {

    public int base;

    public Converter(){
    }

    public int getConvertedToDecimal(String userNumber)
    {
        String[] numberFragments = userNumber.split("");
        return processConvertToDecimal(numberFragments);
    }

    public int processConvertToDecimal(String[] numberFragments)
    {
        int decimalNumber = 0;
        for (int i = numberFragments.length - 1; i >= 0; i--)
        {
            int fragment = Integer.parseInt(numberFragments[i]);
            decimalNumber += fragment * Math.pow(base, i);
        }
        return decimalNumber;
    }

    public String getConvertedFromDecimal(int decimalNumber)
    {
        ArrayList<String> resultValues = processConvertFromDecimal(decimalNumber);
        return resultValues.toString();
    }

    public ArrayList<String> processConvertFromDecimal(int decimalNumber)
    {
        ArrayList<String> valueList = new ArrayList();

        while (decimalNumber > 0)
        {
            int modulo = decimalNumber % base;
            decimalNumber = (decimalNumber - modulo) / base;
            valueList.add(String.valueOf(modulo));
        }
        return valueList;
    }

}

class Binary extends Converter{

    public Binary(){
        base = 2;
    }
}

class Octal extends Converter{

    public Octal(){
        base = 8;
    }
}

class Decimal extends Converter{

    public Decimal(){
        base = 10;
    }

    public int getConvertedToDecimal(String userNumber)
    {
        return Integer.valueOf(userNumber);
    }
}

class Hexadecimal extends Converter{

    public Hexadecimal(){
        base = 16;
    }

    public int getConvertedToDecimal(String userNumber)
    {
        String[] numberFragments = userNumber.split("");
        numberFragments = getNumberFragmentValues(numberFragments);
        return processConvertToDecimal(numberFragments);
    }

    private String[] getNumberFragmentValues(String[] numberFragments)
    {
        for (int i = 0; i < numberFragments.length; i++)
        {
            numberFragments[i] = getMatchingValueFromHexa(numberFragments[i]);
        }
        return numberFragments;
    }

    private String getMatchingValueFromHexa(String value)
    {
        switch (value)
        {
            case "A": return "10";
            case "B": return "11";
            case "C": return "12";
            case "D": return "13";
            case "E": return "14";
            case "F": return "15";
            default: return value;
        }
    }

    public String getConvertedFromDecimal(int decimalNumber)
    {
        ArrayList<String> resultValues = processConvertFromDecimal(decimalNumber);
        resultValues = getMatchingFragments(resultValues);
        return String.join("",resultValues);
    }

    private ArrayList<String> getMatchingFragments(ArrayList<String> resultValues)
    {
        ArrayList<String> matchingResults = new ArrayList<>();

        for (int i = resultValues.size() - 1; i >= 0; i--)
        {
            String matchedHexa = getMatchingHexaFromValue(resultValues.get(i));
            matchingResults.add(matchedHexa);
        }
        return matchingResults;
    }

    private String getMatchingHexaFromValue(String value)
    {
        switch (value)
        {
            case "10": return "A";
            case "11": return "B";
            case "12": return "C";
            case "13": return "D";
            case "14": return "E";
            case "15": return "F";
            default: return value;
        }
    }
}