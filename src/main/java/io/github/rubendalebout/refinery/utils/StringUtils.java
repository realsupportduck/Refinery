package io.github.rubendalebout.refinery.utils;

public class StringUtils {
    /**
     * 
     * @param string Enter the string you want to check here.
     * @return Returns true if the String is empty, false otherwise.
     */
    public boolean isEmpty(String string) {
        return string.isEmpty();
    }
    
    /**
     * 
     * @param string Enter the string you want to check here.
     * @return Returns true if the String is not empty, false otherwise.
     */
    public boolean isNotEmpty(String string) {
        return !string.isEmpty();
    }
    
    /**
     * 
     * @param string Enter the string you want to trim here.
     * @return Returns the string with the whitespaces removed at the start and end.
     */
    public String trim(String string) {
        return string.trim();
    }
    
    /**
     * 
     * @param string Enter the string you want to capitalize here.
     * @return Returns the string with the capitalized characters.
     */
    public String capitalize(String string) {
        return string.toUpperCase();
    }
    
    /**
     * 
     * @param string Enter the string you want the first letter capitalized here.
     * @return Returns the string with the first letter capitalized.
     */
    public String capitalizeFirst(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }
    
    /**
     * 
     * @param string Enter the string you want the first letter of each word capitalized here.
     * @return Returns the string with the first letter capitalized of every word.
     */
    public String capitalizeFirstAll(String string) {
        StringBuilder result = new StringBuilder();
        for (String word : string.split("\\s+"))
            if (!word.isEmpty())
                result.append(Character.toUpperCase(word.charAt(0)))
                    .append(word.substring(1)).append(" ");
        
        return result.toString();
    }
    
    /**
     * 
     * @param string Enter the string you want to decapitalize here.
     * @return Returns the string with the decapitalize characters.
     */
    public String decapitalize(String string) {
        return string.toLowerCase();
    }
    
    /**
     * 
     * @param string Enter the string you want the first letter decapitalize here.
     * @return Returns the string with the first letter decapitalize.
     */
    public String decapitalizeFirst(String string) {
        return string.substring(0, 1).toLowerCase() + string.substring(1);
    }
    
    /**
     * 
     * @param string Enter the string you want the first letter of each word capitalized here.
     * @return Returns the string with the first letter capitalized of every word.
     */
    public String decapitalizeFirstAll(String string) {
        StringBuilder result = new StringBuilder();
        for (String word : string.split("\\s+"))
            if (!word.isEmpty())
                result.append(Character.toLowerCase(word.charAt(0)))
                    .append(word.substring(1)).append(" ");
        
        return result.toString();
    }
    
    /**
     * 
     * @param string Enter the string you want to reverse here.
     * @return Returns the string with all the characters reversed.
     */
    public String reverse(String string) {
        StringBuilder result = new StringBuilder();
        for (char c : string.toCharArray())
            result.insert(0, c);
            
        return result.toString();
    }
    
    /**
     * 
     * @param string Enter the string you want to subtract.
     * @param start Enter the starting index.
     * @param end Enter the end index.
     * @return Returns the subtracted part of the string you entered.
     */
    public String subtract(String string, int start, int end) {
        return string.substring(start, end);
    }
    
    /**
     * 
     * @param string Enter the string you want to subtract.
     * @param delimeter Enter character you want to start.
     * @return Returns the subtracted part of the string you entered.
     */
    public String subtractBefore(String string, String delimeter) {
        return string.substring(0, string.indexOf(delimeter));
    }
    
    /**
     * 
     * @param string Enter the string you want to subtract.
     * @param delimeter Enter character you want to end.
     * @return Returns the subtracted part of the string you entered.
     */
    public String subtractAfter(String string, String delimeter) {
        return string.substring(string.indexOf(delimeter));
    }
    
    /**
     * 
     * @param string Enter the string you want to replace.
     * @param target Enter the specific you want to replace from string.
     * @param replacement Enter the replacement for target.
     * @return Return the replaced string.
     */
    public String replace(String string, String target, String replacement) {
        return string.replace(target, replacement);
    }
    
    /**
     * 
     * @param string Enter the string you want to replace.
     * @param target Enter the specific you want to replace from string.
     * @param replacement Enter the replacement for target.
     * @return Return the replaced string.
     */
    public String replaceAll(String string, String target, String replacement) {
        return string.replaceAll(target, replacement);
    }
    
    /**
     * 
     * @param string Enter the string you want to check on matches.
     * @param length Enter the length of the new string.
     * @param padding Enter the character you want to use.
     * @return Returns the new build string with padding.
     */
    public String leftPad(String string, int length, String padding) {
        StringBuilder padded = new StringBuilder();
        for (int i = 0; i < length - string.length(); i++)
            padded.append(padding);
        
        return padded.append(string).toString();
    }
    
    /**
     * 
     * @param string Enter the string you want to check on matches.
     * @param length Enter the length of the new string.
     * @param padding Enter the character you want to use.
     * @return Returns the new build string with padding.
     */
    public String rightPad(String string, int length, String padding) {
        StringBuilder padded = new StringBuilder();
        for (int i = 0; i < length - string.length(); i++)
            padded.append(padding);
        
        return padded.insert(0, string).toString();
    }
    
    /**
     * 
     * @param string Enter the string you want to check on matches.
     * @param length Enter the length of the new string.
     * @param padding Enter the character you want to use.
     * @return Returns the new build string with padding.
     */
    public String center(String string, int length, String padding) {
        StringBuilder padded = new StringBuilder();
        for (int i = 0; i < (length - string.length())/2; i++)
            padded.append(padding);
        
        return padded + string + padded;
    }
    
    /**
     * 
     * @param string Enter the string where you want to remove accents.
     * @return Returns the new string without accents.
     */
    public String stripAccents(String string) {
        return java.text.Normalizer.normalize(string, java.text.Normalizer.Form.NFD).replaceAll("\\p{M}", "");
    }
    
    /**
     * 
     * @param string Enter the string where you want to remove whitespaces.
     * @return Returns the new string without whitespaces.
     */
    public String stripWhitespaces(String string) {
        return string.replaceAll("\\s", "");
    }
    
    /**
     * 
     * @param string Enter the string where you want to remove entered characters.
     * @param chars Enter the character you want to remove.
     * @return Returns the new string without entered characters.
     */
    public String strip(String string, String chars) {
        return string.replaceAll("[" + chars + "]", "");
    }
    
    /**
     * 
     * @param string Enter the string where you want to remove multiple whitespaces into one.
     * @return Returns the new string without multiple whitespaces.
     */
    public String normalizeSpace(String string) {
        return string.replaceAll("\\s+", " ");
    }
    
    /**
     * 
     * @param string Enter the string where you want sub into.
     * @param open Enter the string of the start.
     * @param close Enter the string of the end.
     * @return Returns the string gab you entered.
     */
    public String substringBetween(String string, String open, String close) {
        return string.substring(string.indexOf(open) + open.length(), string.indexOf(close));
    }
    
    /**
     * 
     * @param string Enter the string where you want a part of.
     * @param remove Enter the part of the string you want remove.
     * @return Returns the reformed string.
     */
    public String removeStart(String string, String remove) {
        return (string.startsWith(remove)) ? string.substring(remove.length()) : string;
    }
    
    /**
     * 
     * @param string Enter the string where you want a part of.
     * @param remove Enter the part of the string you want remove.
     * @return Returns the reformed string.
     */
    public String removeEnd(String string, String remove) {
        return (string.endsWith(remove)) ? string.substring(0, string.length() - remove.length()) : string;
    }
    
    /**
     * 
     * @param string Enter the string that you are going to use.
     * @param prefix Enter the part that will be prepended if it is missing.
     * @return Returns the new string with prefix.
     */
    public String prependIfMissing(String string, String prefix) {
        return (!string.startsWith(prefix)) ? prefix+string : string;
    }
    
    /**
     * 
     * @param string Enter the string that you are going to use.
     * @param suffix Enter the part that will be appended if it is missing.
     * @return Returns the new string with suffix.
     */
    public String appendIfMissing(String string, String suffix) {
        return (!string.endsWith(suffix)) ? string+suffix : string;
    }
    
    /**
     * 
     * @param string Enter the string that you want to wrap.
     * @param wrapper Enter the wrapper you want to use.
     * @return Returns the string with wrapper around it.
     */
    public String wrap(String string, String wrapper) {
        return wrapper+string+wrapper;
    }
    
    /**
     * 
     * @param string Enter the string that you want to unwrap.
     * @param wrapper Enter the wrapper you want to use.
     * @return Returns the string without wrapper around it.
     */
    public String unwrap(String string, String wrapper) {
        return (string.startsWith(wrapper) && string.endsWith(wrapper) && string.length() >= (2*wrapper.length())) ? string.substring(wrapper.length(), string.length() - wrapper.length()) : string;
    }
    
    /**
     * 
     * @param string Enter the string that you want to cut off.
     * @param max Enter the maximum length of the string.
     * @return Returns the string with max length.
     */
    public String abbreviate(String string, int max) {
        return (string.length() > max) ? string.substring(0, max) : string; 
    }
    
    /**
     * 
     * @param array Enter the array you want to join.
     * @param delimeter Enter the character you want to use for the join.
     * @return Returns the string from the join.
     */
    public String join(String[] array, String delimeter) {
        return String.join(delimeter, array);
    }
    
    /**
     * 
     * @param string Enter the string you want to split.
     * @param regex Enter the regular expression you want to split.
     * @return Return the string array.
     */
    public String[] split(String string, String regex) {
        return string.split(regex);
    }
    
    /**
     * 
     * @param string Enter the string you want the search on.
     * @param search Enter the string you want to search with in the string.
     * @return Returns the first index of the searched string.
     */
    public int indexOfIgnoreCase(String string, String search) {
        return string.toLowerCase().indexOf(search);
    }
    
    /**
     * 
     * @param string Enter the string you want the search on.
     * @param search Enter the string you want to search with in the string.
     * @return Returns the first index of the searched string.
     */
    public int indexOf(String string, String search) {
        return string.indexOf(search);
    }
    
    /**
     * 
     * @param string Enter the string you want the search on.
     * @param search Enter the string you want to search with in the string.
     * @return Returns the last index of the searched string.
     */
    public int lastIndexOfIgnoreCase(String string, String search) {
        return string.toLowerCase().lastIndexOf(search);
    }
    
    /**
     * 
     * @param string Enter the string you want the search on.
     * @param search Enter the string you want to search with in the string.
     * @return Returns the last index of the searched string.
     */
    public int lastIndexOf(String string, String search) {
        return string.lastIndexOf(search);
    }
    
    /**
     * 
     * @param string Enter the string you want the search on.
     * @param search Enter the string you want to search with in the string.
     * @return Returns true or false if the string contains the search.
     */
    public boolean containsIgnoreCase(String string, String search) {
        return string.toLowerCase().contains(search);
    }
    
    /**
     * 
     * @param string Enter the string you want the search on.
     * @param search Enter the string you want to search with in the string.
     * @return Returns true or false if the string does not contain the search.
     */
    public boolean notContainsIgnoreCase(String string, String search) {
        return !string.toLowerCase().contains(search);
    }
    
    /**
     * 
     * @param string Enter the string you want to check.
     * @return Returns true or false if the string only contains letters.
     */
    public boolean isAlpha(String string) {
        for (char c : string.toCharArray())
            if (!Character.isLetter(c))
                return false;
        return true;
    }
    
    /**
     * 
     * @param string Enter the string you want to check.
     * @return Returns true or false if the string only contains numbers.
     */
    public boolean isNumeric(String string) {
        for (char c : string.toCharArray())
            if (!Character.isDigit(c))
                return false;
        return true;
    }
    
    /**
     * 
     * @param string Enter the string you want to check.
     * @return Returns true or false if the string only contains numbers and letters.
     */
    public boolean isAlphaNumeric(String string) {
        for (char c : string.toCharArray())
            if (!Character.isDigit(c) && !Character.isLetter(c))
                return false;
        return true;
    }
    
    /**
     * 
     * @param string Enter the string you want to check.
     * @return Returns true or false if the string only contains whitespaces.
     */
    public boolean isWhitespace(String string) {
        for (char c : string.toCharArray())
            if (!Character.isWhitespace(c))
                return false;
        return true;
    }
    
    /**
     * 
     * @param string Enter the string you want to check.
     * @param prefix Enter the prefix you want to check.
     * @return Returns true or false if the string contains the prefix at the start.
     */
    public boolean isPrefix(String string, String prefix) {
        return string.startsWith(prefix);
    }
    
    /**
     * 
     * @param string Enter the string you want to check.
     * @param suffix Enter the suffix you want to check.
     * @return Returns true or false if the string contains the suffix at the end.
     */
    public boolean isSuffix(String string, String suffix) {
        return string.endsWith(suffix);
    }
}