package bibfrog.service;

public class ScandicService {

    public String scandicChecker(String bibtex) {
//    String myName = "domanokz";
        int i = 0;
        int j = 0;
        String newScandicBibtex = "";
        for (char c : bibtex.toCharArray()) {

            if (c == 'ö') {
                newScandicBibtex += bibtex.substring(j, i) + "\\\"o";
                j = i + 1;
                //replace with \"o        
            } else if (c == 'ä') {
                newScandicBibtex += bibtex.substring(j, i) + "\\\"a";
                j = i + 1;
                //replace with \"a     
            } else if (c == 'å') {
                newScandicBibtex += bibtex.substring(j, i) + "\\aa";
                j = i + 1;
                //replace with \aa
            } else if (c == 'Ö') {
                newScandicBibtex += bibtex.substring(j, i) + "\\\"O";
                j = i + 1;
                //replace with \"O    
            } else if (c == 'Ä') {
                newScandicBibtex += bibtex.substring(j, i) + "\\\"A";
                j = i + 1;
                //replace with \"a
            } else if (c == 'Å') {
                newScandicBibtex += bibtex.substring(j, i) + "\\AA";
                j = i + 1;
                //replace with \AA
            }
            i++;
        }
        newScandicBibtex += bibtex.substring(j);
        return newScandicBibtex;
    }

}
