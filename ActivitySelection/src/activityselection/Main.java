package activityselection;

/**
 *
 * @author Aldemar
 */
public class Main {

    int n = 12;
    int activities[][];    
    String selectedActivities_[][];
    

    public Main(int n) {
        activities = new int[n][n];        
        selectedActivities_ = new String[n][n];
    }

    public void initialize() {
        for (int i = 0; i < activities.length; i++) {
            for (int j = 0; j < activities.length; j++) {
                activities[i][j] = 0;
                selectedActivities_[i][j] = "|";
            }
        }
        for (int i = 0; i < activities.length; i++) {
            activities[i][i] = 0;
        }
    }

    public void allActivitySelection(int s[], int f[]) {
        for (int i = 0; i < activities.length - 1; i++) {
            for (int j = i; j < activities.length; j++) {                
                for (int k = i+1; k < j; k++) {
                    if ((f[i] <= s[k]) && (f[k] <= s[j])) {
                        int v = activities[i][k] + activities[k][j] + 1;
                        if (activities[i][j] < v) {
                            activities[i][j] = v;
                            selectedActivities_[i][j] = selectedActivities_[i][j]+","+k;
                        }
                    }
                }
                printActivitities();
            }
        }
    }
   
    public void printActivitities() {
        for (int i = 0; i < activities.length; i++) {
            for (int j = 0; j < activities.length; j++) {
//                System.out.print("\t" + activities[i][j]);
//                System.out.print("\t" + selectedActivities[i][j]);
            }
            System.out.println();
        }
        System.out.println(" ======================================= ");
        for (int i = 0; i < activities.length; i++) {
            for (int j = 0; j < activities.length; j++) {
                System.out.print("\t" + selectedActivities_[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int s[] = {0,  3, 0, 5, 3, 5, 6,   8,  8,  2, 12,Integer.MAX_VALUE}; //Integer.MAX_VALUE}; //
        int f[] = {0,  5, 6, 7, 8, 9, 10, 11, 12, 13, 14,Integer.MAX_VALUE}; //Integer.MAX_VALUE}; //Integer.MAX_VALUE}; //

        Main m = new Main(f.length);

        m.initialize();
        m.allActivitySelection(s, f);
        m.printActivitities();
    }
}
