package TPJenkins;
import java.util.Scanner;

public class DistributeurBillets {

    public static int[] soldesCompte = {1200, 2547, 1458752};

    private static int[] codesCartes = {1111, 2222, 3333};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println(" Veuillez choisir une carte :");
            System.out.println(" ___________________________________ ");
            System.out.println("|           |           |           |");
            System.out.println("|     1     |     2     |     3     |");
            System.out.println("|___________|___________|___________|");
            int cartes = scanner.nextInt();
            if(cartes < 1 || cartes > 3){
                System.out.println("Carte Invalide");
                continue;
            }

            System.out.println(" Veuillez taper votre code secret à 4 chiffres : ");
            System.out.println(" __________________________________________________ ");
            System.out.println("|                |                |                |");
            System.out.println("|       1        |        2       |        3       |");
            System.out.println("|________________|________________|________________|");
            System.out.println("|                |                |                |");
            System.out.println("|       4        |        5       |        6       |");
            System.out.println("|________________|________________|________________|");
            System.out.println("|                |                |                |");
            System.out.println("|       7        |        8       |        9       |");
            System.out.println("|________________|________________|________________|");
            int codes = scanner.nextInt();
            if(codes != codesCartes[cartes-1]){
                System.out.println("Code Incorrect!");
                continue;
            }

            int soldeCourant = soldesCompte[cartes-1];
            System.out.println(" _____________________________________ ");
            System.out.println("|                                     |");
            System.out.printf("|  Solde actuel:         %6d  €    |%n", soldeCourant);
            System.out.println("|_____________________________________|");

            System.out.println("Combien voulez-vous retirer ? ");
            int montantRetire = scanner.nextInt();
            int montantRetired = montantRetire;
            if(montantRetire > soldeCourant){
                System.out.println("Retrait impossible, pour cause de solde insuffisant.");
                continue;
            }

            int[] coupures = {500, 200, 100, 50, 20, 10, 5};
            for (int coupure : coupures){
                int nombreDeBilletsADonner = montantRetire / coupure;
                if(nombreDeBilletsADonner > 0){
                    System.out.println("Impression des billets en cours....");
                    for (int i = 5; i >= 1; i--) {
                        System.out.println(i);
                        try {
                            Thread.sleep(1000); // Attendre 1 seconde
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Vous recevez "+nombreDeBilletsADonner+ " billets de "+coupure+"€");

                    montantRetire = montantRetire % coupure;
                }
            }
            soldesCompte[cartes-1] = soldesCompte[cartes-1] - montantRetired;
            System.out.println("Votre nouveau solde est de "+ soldesCompte[cartes-1]+"€");
            System.out.print("Voulez-vous effectuer une autre opération ? (oui/non) ");
            String reponse = scanner.next();
            if (reponse.equals("non")) {
                break;
            }
        }
        scanner.close();
    }
}
