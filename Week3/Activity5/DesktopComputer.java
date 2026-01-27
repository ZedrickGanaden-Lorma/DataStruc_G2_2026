package DataStruc_G2_2026.Week3.Activity5;

import java.util.ArrayList;

public class DesktopComputer {
    public class PowerSupply {
        String brand;
        String model;
        int wattage;

        void printSpecs() {
            System.out.println("Power Supply");
            System.out.println("Brand   : " + brand);
            System.out.println("Model   : " + model);
            System.out.println("Wattage : " + wattage);
        }
    }

    public class Motherboard {
        String brand;
        String model;

        public class PCIEDevice {
            String type;
            String brand;
            String name;
        }

        public class Memory {
            String brand;
            int size;
            int frequency;

            String getSize() {
                return size + " GB";
            }

            String getFreq() {
                return frequency + " MHz";
            }
        }

        public class Processor {
            String brand;
            String model;
            float maxFrequency;
            float baseFrequency;

            String getFreq() {
                return baseFrequency + " - " + maxFrequency + "GHz";
            }
        }

        ArrayList<PCIEDevice> pcieDevices = new ArrayList<>();
        ArrayList<Memory> memoryDevices = new ArrayList<>();

        void printSpecs() {
            System.out.println("MotherBoard");
            System.out.println("Brand   : " + brand);
            System.out.println("Model   : " + model);
            System.out.println();
            System.out.println();
            System.out.println();
        }
    }

    public class Peripheral {
        String type;
        String brand;
        String name;
    }

    ArrayList<Peripheral> peripherals = new ArrayList<>();
    PowerSupply psu = new PowerSupply();
    Motherboard motherboard = new Motherboard();

}