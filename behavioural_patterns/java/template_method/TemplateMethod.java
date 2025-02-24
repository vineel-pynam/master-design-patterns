package behavioural_patterns.java.template_method;

// Data Parser
abstract class DataParser{
    public final void parseData(){
        readData();
        processData();
        saveData();
        System.out.println();
    }

    abstract void readData();
    abstract void processData();
    abstract void saveData();
    
}

// Concrete Implementations of Data Parser
class CSVParser extends DataParser{

    @Override
    public void readData() {
        System.out.println("Reading CSV Data From File");
    }

    @Override
    public void processData() {
        System.out.println("Processing CSV Data...");
    }

    @Override
    public void saveData(){
        System.out.println("Saving Processed CSV Data");
    }
    
}

class XMLParser extends DataParser{

    @Override
    public void readData() {
        System.out.println("Reading XML Data From File");
    }

    @Override
    public void processData() {
        System.out.println("Processing XML Data...");
    }

    @Override
    public void saveData(){
        System.out.println("Saving Processed XML Data");
    }
    
}


// Client
class TemplateMethod {
    public static void main(String[] args) {
        DataParser csvParser = new CSVParser();
        DataParser xmlParser = new XMLParser();

        csvParser.parseData();
        xmlParser.parseData();
    }
}
