package passgen;

public class PasswordGeneratorSettings {
    private boolean isWithSpecialCharacters;
    private boolean isWithDigits;

    private int passwordLength;

    public PasswordGeneratorSettings() {
        this.passwordLength = 3;
        this.isWithSpecialCharacters = false;
        this.isWithDigits = false;
    }

    public PasswordGeneratorSettings(int passwordLength, boolean isWithSpecialCharacters, boolean isWithDigits) {
        this.passwordLength = passwordLength;
        this.isWithSpecialCharacters = isWithSpecialCharacters;
        this.isWithDigits = isWithDigits;

    }

    public void setPasswordLength(int passwordLength) {
        this.passwordLength = passwordLength;
    }

    public void setWithSpecialCharacters(boolean withSpecialCharacters) {
        isWithSpecialCharacters = withSpecialCharacters;
    }

    public void setWithDigits(boolean withDigits) {
        isWithDigits = withDigits;
    }

    public int getPasswordLength() {
        return passwordLength;
    }

    public boolean isWithSpecialCharacters() {
        return isWithSpecialCharacters;
    }

    public boolean isWithDigits() {
        return isWithDigits;
    }
}
