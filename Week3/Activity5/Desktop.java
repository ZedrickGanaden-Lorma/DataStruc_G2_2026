public class Desktop {
    private static int unitCounter = 0;
    private int unitNum = 0;
    // Motherboard
    private String motherboardBrand = "";
    private String motherboardModel = "";

    // CPU
    private String cpuModel = "";
    private int cpuCores = 0;
    private double cpuClockSpeedGHz = 0;

    // RAM
    private int memorySize = 0;
    private String memoryType = "";

    // Storage
    private int storageSize = 0;
    private String storageType = "";

    // GPU
    private String gpuModel = "";

    // Power
    private int powerSupplyWatts = 0;

    public Desktop() {
        unitNum = unitCounter++;
    }

    public void setMotherboardSpecs(String motherboardBrand, String motherboardModel) {
        this.motherboardBrand = motherboardBrand;
        this.motherboardModel = motherboardModel;
    }

    public void setCPUSpecs(String cpuModel, int cpuCores, double cpuClockSpeedGHz) {
        this.cpuModel = cpuModel;
        this.cpuCores = cpuCores;
        this.cpuClockSpeedGHz = cpuClockSpeedGHz;
    }

    public void setMemorySpecs(int memorySize, String memoryType) {
        this.memorySize = memorySize;
        this.memoryType = memoryType;
    }

    public void setStorageSpecs(int storageSize, String storageType) {
        this.storageSize = storageSize;
        this.storageType = storageType;
    }

    public void setPSUWattage(int powerSupplyWatts) {
        this.powerSupplyWatts = powerSupplyWatts;
    }

    public void displayHeader() {
        System.out.printf("""
                %-15s%-20s%-20s%-20s%-20s%-20s%-20s
                """, "Unit Number", "Motherboard", "CPU", "Memory", "Storage", "GPU", "PSU");
    }

    public String appendUnit(Double n, String unit) {
        if (n <= 0) {
            return "";
        }
        return n + " " + unit;
    }

    public String appendUnit(int n, String unit) {
        if (n <= 0) {
            return "";
        }
        return n + " " + unit;
    }

    public void displaySpecs() {
        String memorySizeString = appendUnit(memorySize, "GB");
        String storageSizeString = appendUnit(storageSize, "GB");
        String psuWattageString = appendUnit(powerSupplyWatts, " W");
        String cpuCoresString = appendUnit(cpuCores, "C");
        String cpuClockSpeedGHzString = appendUnit(cpuClockSpeedGHz, "GHz");
        System.out.printf("""
                %-15s%-20s%-20s%-20s%-20s%-20s%-20s
                """, "Unit #" + unitNum, "Brand : " + motherboardBrand, "Model : " + cpuModel,
                "Size : " + memorySizeString,
                "Size : " + storageSizeString, "Model : " + gpuModel, "Wattage : " + psuWattageString);
        System.out.printf("""
                %-15s%-20s%-20s%-20s%-20s
                """, "", "Model : " + motherboardModel, "Cores : " + cpuCoresString, "Type : " + memoryType,
                "Type : " + storageType);
        System.out.printf("""
                %-15s%-20s%-20s
                """, "", "", "Speed : " + cpuClockSpeedGHzString);
    }

    public void inputMotherboardSpecs() {
        System.out.println("Motherboard");
        this.motherboardBrand = new InputField("Enter brand : ").nextString();
        this.motherboardModel = new InputField("Enter model : ").nextString();
    }

    public void inputCPUSpecs() {
        System.out.println("CPU");
        this.cpuModel = new InputField("Enter model : ").nextString();
        this.cpuCores = new InputField("Enter cores : ").nextInt();
        this.cpuClockSpeedGHz = new InputField("Enter speed : ").nextDouble();
    }

    public void inputMemorySpecs() {
        System.out.println("Memory");
        this.memorySize = new InputField("Enter size : ").nextInt();
        this.memoryType = new InputField("Enter type : ").nextString();
    }

    public void inputStorageSpecs() {
        System.out.println("Storage");
        this.storageSize = new InputField("Enter size : ").nextInt();
        this.storageType = new InputField("Enter type : ").nextString();
    }

    public void inputPSUWattage() {
        System.out.println("Power Supply Unit");
        this.powerSupplyWatts = new InputField("Enter wattage : ").nextInt();
    }

    public void inputAllSpecs() {
        System.out.println("You can enter 0 or enter empty line if no specs");
        inputMotherboardSpecs();
        inputCPUSpecs();
        inputMemorySpecs();
        inputStorageSpecs();
        inputPSUWattage();
    }
}