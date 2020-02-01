package sample;

import java.util.Random;

public class Util {
    //definim valorile asociate fiecarei grupari
    final static String A="00", T="01", C="10", G="11";

    //ASCII to binary
    public static String AsciiToBinary(String asciiString){
        byte[] bytes = asciiString.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes)
        {
            int val = b;
            for (int i = 0; i < 8; i++)
            {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
            // binary.append(' ');
        }
        return binary.toString();
    }

    //Binary to ASCII
    public static String BinaryToString(String binary){
        //padding
        while ( binary.length()%8!=0 ){
            binary = "0"+binary;
        }

        byte[] bytes = binary.getBytes();
        StringBuilder ascii = new StringBuilder();

        String character;

        for(int i=0;i<bytes.length;i+=8)
        {
            character="";
            for(int j=0;j<8;j++)
                character+=bytes[i+j]-'0';

            ascii.append( (char)Integer.parseInt(character, 2) );
        }
        return ascii.toString();
    }

    //Key generation
    public static String getKey(int length){
        String key="";
        String alphabet="";

        //folosim doar litere mari si mici in cheie
        for(char i='a';i<='z';i++)
            alphabet+=i;
        for(char i='A';i<='Z';i++)
            alphabet+=i;

        Random generator=new Random();

        //generam o cheie aleatoare de lungime data
        for(int i=0;i<length;i++){
            key+=(char)alphabet.getBytes()[ generator.nextInt(alphabet.length()) ];
        }

        return key;
    }

    //XOR implementation for strings
    public static String xor(String a, String b){
        if(a.length()!=b.length())
            return null;

        byte[] a_bytes = a.getBytes();
        byte[] b_bytes = b.getBytes();

        String res="";
        for(int i=0;i<a.length();i++)
            res+=(a_bytes[i] ^ b_bytes[i]);

        return res;
    }

    //DNA encoding
    public static String getDna(String source){
        String dna="";

        //padding
        if(source.length()%2==1)
            source="0"+source;

        for(int i=0;i<=source.length()-2;i+=2){
            switch (source.substring(i, i+2)){
                case A:
                    dna+="A";
                    break;
                case T:
                    dna+="T";
                    break;
                case C:
                    dna+="C";
                    break;
                case G:
                    dna+="G";
                    break;
                default:
                    return null;
            }
        }

        return dna;
    }

    //DNA decoding
    public static String getData(String dna){
        String data="";
        for(int i=0;i<dna.length();i++){
            switch (dna.charAt(i)){
                case 'A':
                    data+=A;
                    break;
                case 'T':
                    data+=T;
                    break;
                case 'C':
                    data+=C;
                    break;
                case 'G':
                    data+=G;
                    break;
                default:
                    return null;
            }
        }

        return data;
    }
}
