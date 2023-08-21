package Global;

import java.util.List;
import java.util.Locale;



import model.BonCommande;
import model.TypeAgregats;
import model.User;

public class GlobalVar {
	public static int IDUSER=0;
	public static String NOMUSER;
	public static List<BonCommande> listBonCommande;
	public static User CurentClient;
	public static List<TypeAgregats> listAgregat;
	public static boolean isNumber(String input) {
	    return input.matches("-?\\d+"); // or "-?\\d+(\\.\\d+)?" for decimal numbers
	}
	/*
	 * public static String toLetter(int value){ RuleBasedNumberFormat rbnf = new
	 * RuleBasedNumberFormat(Locale.FRANCE, RuleBasedNumberFormat.SPELLOUT); if
	 * (value == 0) return ""; return rbnf.format(value).toUpperCase() +
	 * " FRANCS CFA"; }
	 */

}
