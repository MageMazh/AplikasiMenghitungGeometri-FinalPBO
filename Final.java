import java.util.Scanner;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Final {
    public static void main(String args[]) {
        //object
        LocalTime timeStrt = LocalTime.now();
        Scanner menu = new Scanner(System.in);
        HashSet<String> handling = new HashSet<String>();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss");
        
        //attribut
        String timeStart = timeStrt.format(myFormatObj);
        int pilihan, cara, a = 1, b = 1, c = 1;
        double nilai, sa, sb, sc, p, l, t;

        //cek adami tidak file txt lalu kasih masuk ke iterator
        Iterator<String> hslCheck = check().iterator();
        while(hslCheck.hasNext()) {
            handling.add(hslCheck.next());
        }

        do {
            menuAwal();
            pilihan = menu.nextInt();
            switch (pilihan) {
                case 1: //lingkaran
                    //buat file
                    try {
                        File fileText = new File("lingkaran.txt");

                        if (!fileText.exists()) {
                            fileText.createNewFile();
                            FileWriter writeText = new FileWriter("lingkaran.txt", true);
                            writeText.write("-----Operasi pada lingkaran-----\n");
                            writeText.close();
                        } else {
                            a = 0;
                            Scanner myReader = new Scanner(fileText);
                            while (myReader.hasNextLine()) {
                                myReader.nextLine();
                                a += 1;
                            }
                            myReader.close();
                        }
                        handling.add("lingkaran.txt");
                    } catch (IOException e) {
                        System.out.println("Ada Masalah yang terjadi");
                        e.printStackTrace();
                    }

                    System.out.print("Masukkan nilai radius: ");
                    validasi(nilai = menu.nextDouble());

                    Lingkaran circle = new Lingkaran();
                    circle.setRadius(nilai);

                    //proses writing file
                    try {                       
                        FileWriter writeText = new FileWriter("lingkaran.txt", true);
 
                        while (true) {
                            menuRumus2D();
                            cara = menu.nextInt();

                            if(cara == 1) {
                                String hasil = a + ". Keliling lingkaran = " + circle.keliling();
                                writeText.write(hasil + "\n");
                                a += 1;
                                break;
                            } else if(cara == 2) {
                                String hasil = a + ". Luas lingkaran = " + circle.luas();
                                writeText.write(hasil + "\n");
                                a += 1;
                                break;
                            } else { //cara tdk sesuai menu
                                invalid();
                                System.out.println("Silahkan masukkan ulang rumus yang ingin digunakan"); 
                            }
                        }
                        System.out.println("Operasi telah berhasil dilakukan!\n");
                        writeText.close();
                    } catch (IOException e) {
                        System.out.println("Ada Masalah yang terjadi");
                        e.printStackTrace();
                    } 
                    //break case 1
                    break;

                case 2: //segitiga

                    //buat file
                    try {
                        File fileText = new File("segitiga.txt");

                        if (!fileText.exists()) {
                            fileText.createNewFile();
                            FileWriter writeText = new FileWriter("segitiga.txt", true);
                            writeText.write("-----Operasi pada segitiga-----\n");
                            writeText.close();
                        } else {
                            b = 0;
                            Scanner myReader = new Scanner(fileText);
                            while (myReader.hasNextLine()) {
                                myReader.nextLine();
                                b += 1;
                            }
                            myReader.close();
                        }
                        handling.add("segitiga.txt");
                        
                    } catch (IOException e) {
                        System.out.println("Ada Masalah yang terjadi");
                        e.printStackTrace();
                    }

                    //write filenya                       
                    try{
                        String[] sgtga = {"Siku-Siku", "Sama Kaki", "Sama Sisi", "Sembarang"};
                        FileWriter writeText = new FileWriter("segitiga.txt", true);

                        while(true) {
                            bentukTriangle();
                            int bt = menu.nextInt();
                        
                            if(bt > sgtga.length) {
                                throw new IndexOutOfBoundsException("Input yang dimasukkan tidak sesuai dengan menu, silahkan ulang lagi");
                            }
                            menuRumus2D();
                            int ms = menu.nextInt();
                            
                            //input nilai sisi a, b, dan c
                            System.out.print("Masukkan nilai sisi a, b, dan c: ");
                            validasi(sa = menu.nextDouble());
                            validasi(sb = menu.nextDouble());
                            validasi(sc = menu.nextDouble());
    
                            if(ms == 1) { 
                                Segitiga triangle = new Segitiga(sa,sb,sc);
                            
                                if((sgtga[bt-1] == "Sama Kaki" && (sa != sc || sa == sb)) || (sgtga[bt-1] == "Sama Sisi" && (sa != sc || sc != sb)) || 
                                (sgtga[bt-1] == "Siku-Siku" && (sc*sc != sa*sa + sb*sb)) || (sgtga[bt-1] == "Sembarang" && (sa == sb || sb == sc || sa == sc))) {
                                    invalid();
                                    System.out.println("Silahkan masukkan ulang sisi yang ingin digunakan");
                                } else {
                                    String hasil = b + ". Keliling Segitiga " + sgtga[bt-1] + " = " + triangle.keliling();
                                    writeText.write(hasil + "\n");
                                    b += 1;   
                                    break;
                                }

                                } else if(ms == 2) {
                                    if (bt == 1 && (sc*sc == sa*sa + sb*sb)) { //siku-siku
                                        SegitigaSiku triangle = new SegitigaSiku(sa,sb,sc);
                                        String hasil = b + ". Luas Segitiga Siku-Siku = " + triangle.luas();
                                        writeText.write(hasil + "\n");
                                        b += 1;
                                        break;
                                    } else if(bt == 2 && sa == sc && sa != sb) { //sama kaki
                                        SegitigaSama triangle = new SegitigaSama(sa,sb,sc);
                                        String hasil = b + ". Luas Segitiga Sama Kaki = " + triangle.luas();
                                        writeText.write(hasil + "\n");
                                        b += 1;
                                        break;
                                    } else if(bt == 3 && (sa == sb) && (sc == sa)) { //Sama sisi
                                        SegitigaSama triangle = new SegitigaSama(sa,sb,sc);
                                        String hasil = b + ". Luas Segitiga Sama Sisi = " + triangle.luas();
                                        writeText.write(hasil + "\n");
                                        b += 1;
                                        break;
                                    } else if(bt == 4 && sa != sb && sb != sc && sa != sc) { //Sembarang
                                        SegitigaSembarang triangle = new SegitigaSembarang(sa,sb,sc);
                                        String hasil = b + ". Luas Segitiga Sembarang = " + triangle.luas();
                                        writeText.write(hasil + "\n");
                                        b += 1;
                                        break;
                                    } else { //syarat sisi ada yang tidak memenuhi
                                        invalid();
                                        System.out.println("Silahkan masukkan ulang sisi yang ingin digunakan");
                                    }
                                } else { // ms bukan 1 atau 2
                                    invalid();
                                    System.out.println("Silahkan masukkan ulang rumus yang ingin digunakan");
                                }
                        } //akhir while

                        System.out.println("Operasi telah berhasil dilakukan!\n");
                        writeText.close();    
                    } catch (IOException e) {
                        System.out.println("Ada Masalah yang terjadi");
                        e.printStackTrace();
                    } 
                
                    break; //akhir case 2
                
                case 3: //balok
                    //buat file
                    try {
                        File fileText = new File("balok.txt");

                        if (!fileText.exists()) {
                            fileText.createNewFile();
                            FileWriter writeText = new FileWriter("balok.txt", true);
                            writeText.write("-----Operasi pada balok-----\n");
                            writeText.close();
                        } else {
                            c = 0;
                            Scanner myReader = new Scanner(fileText);
                            while (myReader.hasNextLine()) {
                                myReader.nextLine();
                                c += 1;
                            }
                            myReader.close();
                        }
                        handling.add("balok.txt");
                        
                    } catch (IOException e) {
                        System.out.println("Ada Masalah yang terjadi");
                        e.printStackTrace();
                    }

                    //write file
                    try {                       
                        FileWriter writeText = new FileWriter("balok.txt", true);
 
                        while (true) {
                            System.out.print("Masukkan nilai panjang, lebar, dan tinggi: ");
                            //input nilai p,l,t
                            validasi(p = menu.nextDouble());
                            validasi(l = menu.nextDouble());
                            validasi(t = menu.nextDouble());
                    
                            menuRumus3D();
                            cara = menu.nextInt();
                         
                            Balok block = new Balok(p,l,t);
                            if(!(p == l && l == t)) {
                                if(cara == 1) {
                                    String hasil = c + ". Keliling balok = " + block.keliling();
                                    writeText.write(hasil + "\n");
                                    c += 1;
                                    break;
                                } else if(cara == 2) {
                                    String hasil = c + ". Luas balok = " + block.luas();
                                    writeText.write(hasil + "\n");
                                    c += 1;
                                    break;
                                } else if(cara == 3) {
                                    String hasil = c + ". Volume balok = " + block.volume();
                                    writeText.write(hasil + "\n");
                                    c += 1;
                                    break;
                                } else { //cara tidak ada dalam menu
                                    invalid();
                                    System.out.println("Silahkan masukkan ulang rumus yang ingin digunakan"); 
                                }
                            } else { //rusuk balok tidak memenuhi syarat
                                    invalid();
                                    System.out.println("Silahkan masukkan ulang panjang rusuk yang ingin digunakan"); 
                            }
                        }
                        
                        System.out.println("Operasi telah berhasil dilakukan!\n");
                        writeText.close();
                    } catch (IOException e) {
                        System.out.println("Ada Masalah yang terjadi");
                        e.printStackTrace();
                    } 
                    break; //akhir case 3
                

                case 4: //selesai               
                    System.out.println("Apakah file.txt-nya mau dihapus? (ya/tidak)");
                    String del = menu.next();

                    LocalTime timend = LocalTime.now();
                    String timeEnd = timend.format(myFormatObj);

                    System.out.println("Waktu mulai aplikasi : " + timeStart);
                    System.out.println("Waktu selesai aplikasi : " + timeEnd);

                    handling.forEach((String i) -> {
                    if(del.equals("ya") || del.toLowerCase().equals("ya")) {
                        readFile(i);
                        delFile(i);
                    } else if (del.equals("tidak") || del.toLowerCase().equals("tidak")) {
                        readFile(i);
      
                    } else {
                        System.out.println("Saya anggap tidak ingin dihapus");
                        readFile(i);

                    }
                    });

                if (del.equals("tidak") || del.toLowerCase().equals("tidak")) {
                    System.out.println("Semua file telah selesai dibaca. Program telah selesai");
                } else {
                    System.out.println("Semua file telah selesai dihapus. Program telah selesai");
                }
                break; //akhir case 4
                
                default: //input selain menu
                    invalid();
            }
        } while (pilihan != 4);
        menu.close();
    }

    static double validasi(double x) {
        if(x > 0) {
            return x;
        } else {
            throw new ArithmeticException("Input yang dimasukkan tidak boleh 0 atau negatif");
        }
    }

    static void readFile(String file) {
        try {
            File myObj = new File(file);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            System.out.println(data);
        }
        myReader.close();
        } catch (FileNotFoundException e) {
                    System.out.println("Ada masalah yang terjadi");
                    e.printStackTrace();
        } 
    }

    static void delFile(String file) {
        File myObj = new File(file); 
        myObj.delete();
    }

    static void invalid() {
        System.out.println("Input yang dimasukkan tidak tepat");
    }

    static ArrayList<String> check() {
        String[] text = {"lingkaran.txt", "segitiga.txt", "balok.txt"};
        ArrayList<String> absen = new ArrayList<String>();
        
        for(String i : text) {
            File myObj = new File(i);
            //jika file txt ada, maka tambahkan ke arraylist absen
            if (myObj.exists()) {
              absen.add(i); 
            }
        }

        return absen;
    }
    
    //list
    static void menuAwal() {
        System.out.println("=======================================");
        System.out.println("    Program Menghitung Geometri");
        System.out.println("=======================================");
        System.out.println("1. Lingkaran");
        System.out.println("2. Segitiga");
        System.out.println("3. Balok");
        System.out.println("4. Selesai (Output dikeluarkan)");
        System.out.print("Bangun apa yang ingin dihitung(1-4) : ");
    }
    static void bentukTriangle() {
        System.out.println("\n1. Segitiga Siku-Siku");
        System.out.println("2. Segitiga Sama Kaki");
        System.out.println("3. Segitiga Sama Sisi");
        System.out.println("4. Segitiga Sembarang");
        System.out.print("Bentuk apa yang ingin digunakan(1-4) : ");
    }

    static void menuRumus2D() {
        System.out.println("\n1. Keliling");
        System.out.println("2. Luas");
        System.out.print("Rumus apa yang ingin digunakan(1-2) : ");
    }
    static void menuRumus3D() {
        System.out.println("\n1. Keliling");
        System.out.println("2. Luas");
        System.out.println("3. Volume");
        System.out.print("Rumus apa yang ingin digunakan(1-3) : ");
    }

}
