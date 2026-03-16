public class Desktop {
    private static int unitCounter = 0;
    private int unitNum = 0;
    // Motherboard
    private String motherboardBrand = "";
    private String motherboardModel = "";

    // CPU
    private String cpuModel = "";
    private int cpuCores = 0;
    private double cpuClockGHz = 0;

    // RAM
    private int memorySize = 0;
    private String memoryType = "";

    // Storage
    private int storageSize = 0;
    private String storageType = "";

    // GPU
    private String gpuModel = "";

    // Power
    private int powerSupplyWattage = 0;

    public int getUnitNum() {
        return unitNum;
    }

    public String getMotherboardBrand() {
        return motherboardBrand;
    }

    public String getMotherboardModel() {
        return motherboardModel;
    }

    public String getCpuModel() {
        return cpuModel;
    }

    public int getCpuCores() {
        return cpuCores;
    }

    public double getCpuClockGHz() {
        return cpuClockGHz;
    }

    public int getMemorySize() {
        return memorySize;
    }

    public String getMemoryType() {
        return memoryType;
    }

    public int getStorageSize() {
        return storageSize;
    }

    public String getStorageType() {
        return storageType;
    }

    public String getGpuModel() {
        return gpuModel;
    }

    public int getPowerSupplyWattage() {
        return powerSupplyWattage;
    }

    public Desktop() {
        unitNum = unitCounter++;
    }

    public void setMotherboardSpecs(String motherboardBrand, String motherboardModel) {
        this.motherboardBrand = motherboardBrand;
        this.motherboardModel = motherboardModel;
    }

    public void setMotherboardBrand(String motherboardBrand) {
        this.motherboardBrand = motherboardBrand;
    }

    public void setMotherboardModel(String motherboardModel) {
        this.motherboardModel = motherboardModel;
    }

    public void setCPUSpecs(String cpuModel, int cpuCores, double cpuClockSpeedGHz) {
        this.cpuModel = cpuModel;
        this.cpuCores = cpuCores;
        this.cpuClockGHz = cpuClockSpeedGHz;
    }

    public void setCPUModel(String cpuModel) {
        this.cpuModel = cpuModel;
    }

    public void setCPUClockSpeedGHz(double cpuClockSpeedGHz) {
        this.cpuClockGHz = cpuClockSpeedGHz;
    }

    public void setCPUCores(int cpuCores) {
        this.cpuCores = cpuCores;
    }

    public void setMemorySpecs(int memorySize, String memoryType) {
        this.memorySize = memorySize;
        this.memoryType = memoryType;
    }

    public void setMemorySize(int memorySize) {
        this.memorySize = memorySize;
    }

    public void setMemoryType(String memoryType) {
        this.memoryType = memoryType;
    }

    public void setGPUModel(String gpuModel) {
        this.gpuModel = gpuModel;
    }

    public void setStorageSpecs(int storageSize, String storageType) {
        this.storageSize = storageSize;
        this.storageType = storageType;
    }

    public void setStorageSize(int storageSize) {
        this.storageSize = storageSize;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    public void setPSUWattage(int powerSupplyWatts) {
        this.powerSupplyWattage = powerSupplyWatts;
    }

    public static String getCSVHeader() {
        return "unitNumber,MotherboardBrand,MotherboardModel,CPUModel,CPUCores,CPUSpeedGHz,MemorySizeGB,MemoryType,StorageSizeGB,StorageType,GPUModel,PSUWattage";
    }

    public String getCSVData() {
        return unitNum + "," + motherboardBrand + "," + motherboardModel + "," + cpuModel + "," + cpuCores + ","
                + cpuClockGHz + "," + memorySize + "," + memoryType + "," + storageSize + "," + storageType + ","
                + gpuModel + "," + powerSupplyWattage;
    }

    public static void displayHeader() {
        System.out.printf("""
                %-15s%-30s%-30s%-20s%-20s%-20s%-20s
                """, "Unit Number", "Motherboard", "CPU", "Memory", "Storage", "GPU", "PSU");
    }

    private String appendUnit(double n, String unit) {
        if (n <= 0) {
            return "";
        }
        return n + " " + unit;
    }

    private String appendUnit(int n, String unit) {
        if (n <= 0) {
            return "";
        }
        return n + " " + unit;
    }

    public void displaySpecs() {
        String memorySizeString = appendUnit(memorySize, "GB");
        String storageSizeString = appendUnit(storageSize, "GB");
        String psuWattageString = appendUnit(powerSupplyWattage, "W");
        String cpuCoresString = appendUnit(cpuCores, "C");
        String cpuClockGHzString = appendUnit(cpuClockGHz, "GHz");
        System.out.printf("""
                %-15s%-30s%-30s%-20s%-20s%-20s%-20s
                """, "Unit #" + unitNum, "Brand : " + motherboardBrand, "Model : " + cpuModel,
                "Size : " + memorySizeString,
                "Size : " + storageSizeString, "Model : " + gpuModel, "Wattage : " + psuWattageString);
        System.out.printf("""
                %-15s%-30s%-30s%-20s%-20s
                """, "", "Model : " + motherboardModel, "Cores : " + cpuCoresString, "Type : " + memoryType,
                "Type : " + storageType);
        System.out.printf("""
                %-15s%-30s%-30s
                """, "", "", "Speed : " + cpuClockGHzString);
    }

    public void inputMotherboardSpecs() {
        System.out.println("Motherboard");
        inputMotherboardBrand();
        inputMotherboardModel();
    }

    public void inputCPUSpecs() {
        System.out.println("CPU");
        inputCPUModel();
        inputCPUCores();
        inputCPUClockGHz();
    }

    public void inputMemorySpecs() {
        System.out.println("Memory");
        inputMemorySize();
        inputMemoryType();
    }

    public void inputStorageSpecs() {
        System.out.println("Storage");
        inputStorageSize();
        inputStorageType();
    }

    public void inputGPUSpecs() {
        System.out.println("Graphics Processing Unit");
        this.gpuModel = new InputField("Enter model : ").nextString();
    }

    public void inputPowerSupplyWattage() {
        System.out.println("Power Supply Unit");
        this.powerSupplyWattage = new InputField("Enter wattage : ").min(20).nextInt();
    }

    public void inputAllSpecs() {
        System.out.println("You can enter 0 or enter empty line if no specs");
        inputMotherboardSpecs();
        inputCPUSpecs();
        inputMemorySpecs();
        inputStorageSpecs();
        inputGPUSpecs();
        inputPowerSupplyWattage();
    }

    public void inputMotherboardBrand() {
        this.motherboardBrand = new InputField("Enter brand : ").nextString();
    }

    public void inputMotherboardModel() {
        this.motherboardModel = new InputField("Enter model : ").nextString();
    }

    public void inputCPUModel() {
        this.cpuModel = new InputField("Enter model : ").nextString();
    }

    public void inputCPUCores() {
        this.cpuCores = new InputField("Enter cores : ").min(1).nextInt();
    }

    public void inputCPUClockGHz() {
        this.cpuClockGHz = new InputField("Enter speed : ").min(1).nextDouble();
    }

    public void inputMemorySize() {
        this.memorySize = new InputField("Enter size : ").min(1).nextInt();
    }

    public void inputMemoryType() {
        this.memoryType = new InputField("Enter type : ").nextString();
    }

    public void inputStorageSize() {
        this.storageSize = new InputField("Enter size : ").min(1).nextInt();
    }

    public void inputStorageType() {
        this.storageType = new InputField("Enter type : ").nextString();
    }
}