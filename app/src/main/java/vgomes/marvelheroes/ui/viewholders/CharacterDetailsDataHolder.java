package vgomes.marvelheroes.ui.viewholders;

/**
 * Created by victorgomes on 02/07/17.
 */

public class CharacterDetailsDataHolder {

    String label;
    String type;

    public CharacterDetailsDataHolder(String label, String type) {
        this.label = label;
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public String getType() {
        return type;
    }
}
