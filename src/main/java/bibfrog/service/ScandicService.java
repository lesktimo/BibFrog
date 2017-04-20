package bibfrog.service;

public class ScandicService {

    public String scandicChecker(String bibtex) {
//    String myName = "domanokz";
        int i = 0;
        int j = 0;
        String newScandicBibtex = "";
        for (char c : bibtex.toCharArray()) {

            switch (c) {
                case 'ö':
                    newScandicBibtex += bibtex.substring(j, i) + "\\\"o";
                    j = i + 1;
                    //replace with \"o        
                    break;
                case 'ä':
                    newScandicBibtex += bibtex.substring(j, i) + "\\\"a";
                    j = i + 1;
                    //replace with \"a     
                    break;
                case 'å':
                    newScandicBibtex += bibtex.substring(j, i) + "\\aa";
                    j = i + 1;
                    //replace with \aa
                    break;
                case 'Ö':
                    newScandicBibtex += bibtex.substring(j, i) + "\\\"O";
                    j = i + 1;
                    //replace with \"O    
                    break;
                case 'Ä':
                    newScandicBibtex += bibtex.substring(j, i) + "\\\"A";
                    j = i + 1;
                    //replace with \"a
                    break;
                case 'Å':
                    newScandicBibtex += bibtex.substring(j, i) + "\\AA";
                    j = i + 1;
                    //replace with \AA
                    break;
                default:
                    break;
            }
            i++;
        }
        newScandicBibtex += bibtex.substring(j);
        return newScandicBibtex;
    }

}
