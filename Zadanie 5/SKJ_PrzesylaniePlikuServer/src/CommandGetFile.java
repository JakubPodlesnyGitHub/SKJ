public class CommandGetFile extends Commands{
    private String fileName;
    public static String getKeyWord(){
        return "PLIK";
    }
    public CommandGetFile(String fileName) {
        this.fileName = fileName;
    }

    public String getArgument(){
        return fileName;
    }
}
