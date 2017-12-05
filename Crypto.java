public class Crypto {

    public static void main(String[] args) {
    String one= encryptString("This is some \"really\" great. (Text)!?",-1,5);
    String two = decryptString(one,-1);

    System.out.println("Encrypted text: "+one);
    System.out.println("Decrypted text: "+two);
    }

    public static String normalizeText(String s) {
        String special = " . , : ; ’ \" ! ? ( )";           //remove spaces and punctuation
        for (int i = 0; i < special.length(); i++) {
            s = s.replace(special.substring(i, i + 1), "");
        }
        s = s.toUpperCase();                                //to upper case
        return s;
    }

    public static String obify(String s){
        String sObified="";
        for(int i = 0; i< s.length();i++){
            if(s.charAt(i)=='A'||s.charAt(i)=='O'||s.charAt(i)=='E'         //check for vowels
                    ||s.charAt(i)=='I'||s.charAt(i)=='U'||s.charAt(i)=='Y'){
                sObified+="OB"+s.substring(i,i+1);
            }
            else sObified+=s.substring(i,i+1);
        }
        return sObified;
    }

    public static String caesarify(String plainText, int key) {

        String alphabet=shiftAlphabet(0);
        String newAlphabet = shiftAlphabet(key);
        String cypherText="";

        /*for each letter in plainText find it's index in regular alphabet
          find letter of that index in shifted alphabet and to cypherText */
        for(int j=0; j<plainText.length();j++) {
            int index = alphabet.indexOf(plainText.charAt(j));
            cypherText += newAlphabet.charAt(index);
        }
        return cypherText;
    }

    public static String shiftAlphabet(int shift) {
        int start = 0;
        if (shift < 0) {
            start = (int) 'Z' + shift + 1;
        } else {
            start = 'A' + shift;
        }
        String result = "";
        char currChar = (char) start;
        for (; currChar <= 'Z'; ++currChar) {
            result = result + currChar;
        }
        if (result.length() < 26) {
            for (currChar = 'A'; result.length() < 26; ++currChar) {
                result = result + currChar;
            }
        }
        return result;
    }

    public static String groupify(String code, int n) {

        int length = code.length();

        while(length%n!=0){
            code+="x";                  //add padding if necessary
            length = code.length();
        }

        String groupCode="";                    //divide into groups
        for(int i=0;i<code.length()-n;i=i+n){
            groupCode+=code.substring(i,i+n)+" ";
        }
        groupCode+=code.substring(code.length()-n,code.length());

        return groupCode;
    }

    public static String encryptString(String plainText, int key, int groupSize) {
        String norm = normalizeText(plainText);
        String obif = obify(norm);
        String cypher = caesarify(obif,key);
        String group = groupify(cypher,groupSize);
        return group;
    }

    //DECRYPT CYPHER

    public static String ungroupify(String cypher){
        cypher = cypher.replace(" ","");    //remove spaces between groups
        if(cypher.contains("x")){                               //remove 'x'-s if found any
            cypher=cypher.substring(0,cypher.indexOf('x'));}
        return cypher;
    }

    public static String undoCaesar(String cypher, int key){
        int length=cypher.length();
        String alphabet=shiftAlphabet(key);
        String newAlphabet = shiftAlphabet(0);
        String plainText="";

        /*for each letter in cypher find it's index in shifted alphabet
          find letter of that index in regular alphabet and to plainText */
        for(int i=0; i<length;i++) {
            int index = alphabet.indexOf(cypher.charAt(i));
            plainText += newAlphabet.charAt(index);
        }

        return plainText;
    }

    public static String unObify(String s){
        s=s.replace("OB","");
        return s;
    }

    public static String decryptString(String cypherText, int key) {
        String spaceFreeCypherText=ungroupify(cypherText);
        String unCaesarify = undoCaesar(spaceFreeCypherText, key);
        String unObify=unObify(unCaesarify);
        return unObify;
    }

}

