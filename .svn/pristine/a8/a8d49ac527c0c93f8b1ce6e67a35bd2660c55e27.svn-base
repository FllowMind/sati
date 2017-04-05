package com.jyu.sati.common.util;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

/**
 * Useful String tools extended from StringUtils in projects of Jakarta &
 * opensymphony.
 * 
 * @see org.apache.commons.lang.StringUtils
 * @see org.springframework.util.StringUtils
 */
public class StringUtil extends StringUtils {

    public static final String FOLDER_SEPARATOR = "/";
    public static final String WINDOWS_FOLDER_SEPARATOR = "\\";
    public static final String TOP_PATH = "..";
    public static final String CURRENT_PATH = ".";
    public static final char EXTENSION_SEPARATOR = '.';
    public static final char NESTED_DELIM = '.';
    public static final String ARRAY_DELIM = ",";
    public static final String KEYVALUE_DELIM = "=";
    public static final String CARRAY_LINEFEED = "\n";
    public static final String PLACEHOLDER_PREFIX = "${";
    public static final String PLACEHOLDER_SUFFIX = "}";

    private StringUtil() {
    }

    /**
     * Check that the given String is neither <code>null</code> nor of length
     * 0. Note: Will return <code>true</code> for a String that purely
     * consists of whitespace.
     * <p>
     * 
     * <pre>
     * StringUtils.hasLength(null) = false
     * StringUtils.hasLength(&quot;&quot;) = false
     * StringUtils.hasLength(&quot; &quot;) = true
     * StringUtils.hasLength(&quot;Hello&quot;) = true
     * </pre>
     * 
     * @param str
     *            the String to check (may be <code>null</code>)
     * @return <code>true</code> if the String is not null and has length
     * @see #hasText(String)
     */
    public static boolean hasLength(String str) {
        return (str != null && str.length() > 0);
    }

    /**
     * Trim leading whitespace from the given String.
     * 
     * @param str
     *            the String to check
     * @return the trimmed String
     * @see java.lang.Character#isWhitespace
     */
    public static String ltrim(String str) {
        if (!hasLength(str)) {
            return str;
        }
        StringBuffer buf = new StringBuffer(str);
        while (buf.length() > 0 && Character.isWhitespace(buf.charAt(0))) {
            buf.deleteCharAt(0);
        }
        return buf.toString();
    }

    /**
     * Trim all occurrences of the supplied leading character from the given
     * String.
     * 
     * @param str
     *            the String to check
     * @param leadingCharacter
     *            the leading character to be trimmed
     * @return the trimmed String
     */
    public static String trimLeadingCharacter(String str, char leadingCharacter) {
        if (!hasLength(str)) {
            return str;
        }
        StringBuffer buf = new StringBuffer(str);
        while (buf.length() > 0 && buf.charAt(0) == leadingCharacter) {
            buf.deleteCharAt(0);
        }
        return buf.toString();
    }

    /**
     * Trim trailing whitespace from the given String.
     * 
     * @param str
     *            the String to check
     * @return the trimmed String
     * @see java.lang.Character#isWhitespace
     */
    public static String rtrim(String str) {
        if (!hasLength(str)) {
            return str;
        }
        StringBuffer buf = new StringBuffer(str);
        while (buf.length() > 0
                && Character.isWhitespace(buf.charAt(buf.length() - 1))) {
            buf.deleteCharAt(buf.length() - 1);
        }
        return buf.toString();
    }

    /**
     * Test if the given String starts with the specified prefix, ignoring
     * upper/lower case.
     * 
     * @param str
     *            the String to check
     * @param prefix
     *            the prefix to look for
     * @see java.lang.String#startsWith
     */
    public static boolean startsWithIgnoreCase(String str, String prefix) {
        if (str == null || prefix == null) {
            return false;
        }
        if (str.startsWith(prefix)) {
            return true;
        }
        if (str.length() < prefix.length()) {
            return false;
        }
        String lcStr = str.substring(0, prefix.length()).toLowerCase();
        String lcPrefix = prefix.toLowerCase();
        return lcStr.equals(lcPrefix);
    }

    /**
     * Test if the given String ends with the specified suffix, ignoring
     * upper/lower case.
     * 
     * @param str
     *            the String to check
     * @param suffix
     *            the suffix to look for
     * @see java.lang.String#endsWith
     */
    public static boolean endsWithIgnoreCase(String str, String suffix) {
        if (str == null || suffix == null) {
            return false;
        }
        if (str.endsWith(suffix)) {
            return true;
        }
        if (str.length() < suffix.length()) {
            return false;
        }

        String lcStr = str.substring(str.length() - suffix.length())
                .toLowerCase();
        String lcSuffix = suffix.toLowerCase();
        return lcStr.equals(lcSuffix);
    }

    /**
     * Compares two Strings, returning true if they are equal. null safe, and
     * the second String should be a array, any of String in the array equals to
     * the first String will make true returned.
     * 
     * @param str1
     *            the first String, may be null
     * @param strs
     *            the second String array, may be null
     * @return true if str1 equals to any of second one
     */
    public static boolean equals(String str1, String[] strs) {
        if (null == strs || strs.length == 0)
            return false;
        for (int i = 0; i < strs.length; i++) {
            if (StringUtils.equals(str1, strs[i]))
                return true;
        }
        return false;
    }

    /**
     * Compares first with a String array, return the index of match String in
     * array.
     * 
     * @param str1
     *            the String to switch
     * @param strs
     *            the String array
     * @return -1 if array is null or none matched
     */
    public static int equalsIndex(String str1, String[] strs) {
        if (null == strs || strs.length == 0)
            return -1;
        for (int i = 0; i < strs.length; i++) {
            if (StringUtils.equals(str1, strs[i]))
                return i;
        }
        return -1;
    }

    /**
     * Quote the given String with single quotes.
     * 
     * @param str
     *            the input String (e.g. "myString")
     * @return the quoted String (e.g. "'myString'"), or
     *         <code>null<code> if the input was <code>null</code>
     */
    public static String quote(String str) {
        return (str != null ? "'" + str + "'" : null);
    }

    /**
     * Quote the given String with double quotes.
     * 
     * @param str
     *            the input String (e.g. myString)
     * @return the quoted String (e.g. "myString"), or
     *         <code>null<code> if the input was <code>null</code>
     */
    public static String dbquote(String str) {
        return (str != null ? "\"" + str + "\"" : null);
    }

    /**
     * Capitalize a <code>String</code>, changing the first letter to upper
     * case as per {@link Character#toUpperCase(char)}. No other letters are
     * changed.
     * 
     * @param str
     *            the String to capitalize, may be <code>null</code>
     * @return the capitalized String, <code>null</code> if null
     */
    public static String capitalize(String str) {
        return changeFirstCharacterCase(str, true);
    }

    /**
     * Uncapitalize a <code>String</code>, changing the first letter to lower
     * case as per {@link Character#toLowerCase(char)}. No other letters are
     * changed.
     * 
     * @param str
     *            the String to uncapitalize, may be <code>null</code>
     * @return the uncapitalized String, <code>null</code> if null
     */
    public static String uncapitalize(String str) {
        return changeFirstCharacterCase(str, false);
    }

    private static String changeFirstCharacterCase(String str,
            boolean capitalize) {
        if (str == null || str.length() == 0) {
            return str;
        }
        StringBuffer buf = new StringBuffer(str.length());
        if (capitalize) {
            buf.append(Character.toUpperCase(str.charAt(0)));
        } else {
            buf.append(Character.toLowerCase(str.charAt(0)));
        }
        buf.append(str.substring(1));
        return buf.toString();
    }

    /**
     * Delete any character in a given String.
     * 
     * @param inString
     *            the original String
     * @param charsToDelete
     *            a set of characters to delete. E.g. "az\n" will delete 'a's,
     *            'z's and new lines.
     * @return the resulting String
     */
    public static String deleteAny(String inString, String charsToDelete) {
        if (!hasLength(inString) || !hasLength(charsToDelete)) {
            return inString;
        }
        StringBuffer out = new StringBuffer();
        for (int i = 0; i < inString.length(); i++) {
            char c = inString.charAt(i);
            if (charsToDelete.indexOf(c) == -1) {
                out.append(c);
            }
        }
        return out.toString();
    }

    /**
     * Extract the filename from the given path, e.g. "mypath/myfile.txt" ->
     * "myfile.txt".
     * 
     * @param path
     *            the file path (may be <code>null</code>)
     * @return the extracted filename, or <code>null</code> if none
     */
    public static String getFilename(String path) {
        if (path == null) {
            return null;
        }
        int separatorIndex = path.lastIndexOf(FOLDER_SEPARATOR);
        return (separatorIndex != -1 ? path.substring(separatorIndex + 1)
                : path);
    }

    /**
     * Extract the filename extension from the given path, e.g.
     * "mypath/myfile.txt" -> "txt".
     * 
     * @param path
     *            the file path (may be <code>null</code>)
     * @return the extracted filename extension, or <code>null</code> if none
     */
    public static String getFilenameExtension(String path) {
        if (path == null) {
            return null;
        }
        int sepIndex = path.lastIndexOf(EXTENSION_SEPARATOR);
        return (sepIndex != -1 ? path.substring(sepIndex + 1) : null);
    }

    /**
     * Strip the filename extension from the given path, e.g.
     * "mypath/myfile.txt" -> "mypath/myfile".
     * 
     * @param path
     *            the file path (may be <code>null</code>)
     * @return the path with stripped filename extension, or <code>null</code>
     *         if none
     */
    public static String stripFilenameExtension(String path) {
        if (path == null) {
            return null;
        }
        int sepIndex = path.lastIndexOf(EXTENSION_SEPARATOR);
        return (sepIndex != -1 ? path.substring(0, sepIndex) : path);
    }

    /**
     * Apply the given relative path to the given path, assuming standard Java
     * folder separation (i.e. "/" separators);
     * 
     * @param path
     *            the path to start from (usually a full file path)
     * @param relativePath
     *            the relative path to apply (relative to the full file path
     *            above)
     * @return the full file path that results from applying the relative path
     */
    public static String applyRelativePath(String path, String relativePath) {
        int separatorIndex = path.lastIndexOf(FOLDER_SEPARATOR);
        if (separatorIndex != -1) {
            String newPath = path.substring(0, separatorIndex);
            if (!relativePath.startsWith(FOLDER_SEPARATOR)) {
                newPath += FOLDER_SEPARATOR;
            }
            return newPath + relativePath;
        } else {
            return relativePath;
        }
    }

    /**
     * Normalize the path by suppressing sequences like "path/.." and inner
     * simple dots.
     * <p>
     * The result is convenient for path comparison. For other uses, notice that
     * Windows separators ("\") are replaced by simple slashes.
     * 
     * @param path
     *            the original path
     * @return the normalized path
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static String cleanPath(String path) {
        String pathToUse = replace(path, WINDOWS_FOLDER_SEPARATOR,
                FOLDER_SEPARATOR);

        // Strip prefix from path to analyze, to not treat it as part of the
        // first path element. This is necessary to correctly parse paths like
        // "file:core/../core/io/Resource.class", where the ".." should just
        // strip the first "core" directory while keeping the "file:" prefix.
        int prefixIndex = pathToUse.indexOf(":");
        String prefix = "";
        if (prefixIndex != -1) {
            prefix = pathToUse.substring(0, prefixIndex + 1);
            pathToUse = pathToUse.substring(prefixIndex + 1);
        }

        String[] pathArray = delimitedListToStringArray(pathToUse,
                FOLDER_SEPARATOR);
        List pathElements = new LinkedList();
        int tops = 0;

        for (int i = pathArray.length - 1; i >= 0; i--) {
            if (CURRENT_PATH.equals(pathArray[i])) {
                // Points to current directory - drop it.
            } else if (TOP_PATH.equals(pathArray[i])) {
                // Registering top path found.
                tops++;
            } else {
                if (tops > 0) {
                    // Merging path element with corresponding to top path.
                    tops--;
                } else {
                    // Normal path element found.
                    pathElements.add(0, pathArray[i]);
                }
            }
        }

        // Remaining top paths need to be retained.
        for (int i = 0; i < tops; i++) {
            pathElements.add(0, TOP_PATH);
        }

        return prefix
                + collectionToDelimitedString(pathElements, FOLDER_SEPARATOR);
    }

    /**
     * Compare two paths after normalization of them.
     * 
     * @param path1
     *            first path for comparison
     * @param path2
     *            second path for comparison
     * @return whether the two paths are equivalent after normalization
     */
    public static boolean pathEquals(String path1, String path2) {
        return cleanPath(path1).equals(cleanPath(path2));
    }

    /**
     * Tokenize the given String into a String array via a StringTokenizer.
     * Trims tokens and omits empty tokens.
     * <p>
     * The given delimiters string is supposed to consist of any number of
     * delimiter characters. Each of those characters can be used to separate
     * tokens. A delimiter is always a single character; for multi-character
     * delimiters, consider using <code>delimitedListToStringArray</code>
     * 
     * @param str
     *            the String to tokenize
     * @param delimiters
     *            the delimiter characters, assembled as String (each of those
     *            characters is individually considered as delimiter).
     * @return an array of the tokens
     * @see java.util.StringTokenizer
     * @see java.lang.String#trim()
     * @see #delimitedListToStringArray
     */
    public static String[] tokenizeToStringArray(String str, String delimiters) {
        return tokenizeToStringArray(str, delimiters, true, true);
    }

    /**
     * Tokenize the given String into a String array via a StringTokenizer.
     * <p>
     * The given delimiters string is supposed to consist of any number of
     * delimiter characters. Each of those characters can be used to separate
     * tokens. A delimiter is always a single character; for multi-character
     * delimiters, consider using <code>delimitedListToStringArray</code>
     * 
     * @param str
     *            the String to tokenize
     * @param delimiters
     *            the delimiter characters, assembled as String (each of those
     *            characters is individually considered as delimiter)
     * @param trimTokens
     *            trim the tokens via String's <code>trim</code>
     * @param ignoreEmptyTokens
     *            omit empty tokens from the result array (only applies to
     *            tokens that are empty after trimming; StringTokenizer will not
     *            consider subsequent delimiters as token in the first place).
     * @return an array of the tokens (<code>null</code> if the input String
     *         was <code>null</code>)
     * @see java.util.StringTokenizer
     * @see java.lang.String#trim()
     * @see #delimitedListToStringArray
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static String[] tokenizeToStringArray(String str, String delimiters,
            boolean trimTokens, boolean ignoreEmptyTokens) {

        if (str == null) {
            return null;
        }
        StringTokenizer st = new StringTokenizer(str, delimiters);
        List tokens = new ArrayList();
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (trimTokens) {
                token = token.trim();
            }
            if (!ignoreEmptyTokens || token.length() > 0) {
                tokens.add(token);
            }
        }
        return toStringArray(tokens);
    }

    /**
     * Parse the given <code>localeString</code> into a {@link Locale}.
     * <p>
     * This is the inverse operation of
     * {@link Locale#toString Locale's toString}.
     * 
     * @param localeString
     *            the locale string, following <code>Locale's</code>
     * <code>toString()</code>
     *            format ("en", "en_UK", etc); also accepts spaces as
     *            separators, as an alternative to underscores
     * @return a corresponding <code>Locale</code> instance
     */
    public static Locale parseLocaleString(String localeString) {
        String[] parts = tokenizeToStringArray(localeString, "_ ", false, false);
        String language = (parts.length > 0 ? parts[0] : "");
        String country = (parts.length > 1 ? parts[1] : "");
        String variant = "";
        if (parts.length >= 2) {
            // There is definitely a variant, and it is everything after the
            // country
            // code sans the separator between the country code and the variant.
            int endIndexOfCountryCode = localeString.indexOf(country)
                    + country.length();
            // Strip off any leading '_' and whitespace, what's left is the
            // variant.
            variant = ltrim(localeString.substring(endIndexOfCountryCode));
            if (variant.startsWith("_")) {
                variant = trimLeadingCharacter(variant, '_');
            }
        }
        return (language.length() > 0 ? new Locale(language, country, variant)
                : null);
    }

    /**
     * Take a String which is a delimited list and convert it to a String array.
     * <p>
     * A single delimiter can consists of more than one character: It will still
     * be considered as single delimiter string, rather than as bunch of
     * potential delimiter characters - in contrast to
     * <code>tokenizeToStringArray</code>.
     * 
     * @param str
     *            the input String
     * @param delimiter
     *            the delimiter between elements (this is a single delimiter,
     *            rather than a bunch individual delimiter characters)
     * @return an array of the tokens in the list
     * @see #tokenizeToStringArray
     */
    public static String[] delimitedListToStringArray(String str,
            String delimiter) {
        return delimitedListToStringArray(str, delimiter, null);
    }

    /**
     * Take a String which is a delimited list and convert it to a String array.
     * <p>
     * A single delimiter can consists of more than one character: It will still
     * be considered as single delimiter string, rather than as bunch of
     * potential delimiter characters - in contrast to
     * <code>tokenizeToStringArray</code>.
     * 
     * @param str
     *            the input String
     * @param delimiter
     *            the delimiter between elements (this is a single delimiter,
     *            rather than a bunch individual delimiter characters)
     * @param charsToDelete
     *            a set of characters to delete. Useful for deleting unwanted
     *            line breaks: e.g. "\r\n\f" will delete all new lines and line
     *            feeds in a String.
     * @return an array of the tokens in the list
     * @see #tokenizeToStringArray
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static String[] delimitedListToStringArray(String str,
            String delimiter, String charsToDelete) {
        if (str == null) {
            return new String[0];
        }
        if (delimiter == null) {
            return new String[] { str };
        }
        List result = new ArrayList();
        if ("".equals(delimiter)) {
            for (int i = 0; i < str.length(); i++) {
                result.add(deleteAny(str.substring(i, i + 1), charsToDelete));
            }
        } else {
            int pos = 0;
            int delPos = 0;
            while ((delPos = str.indexOf(delimiter, pos)) != -1) {
                result.add(deleteAny(str.substring(pos, delPos), charsToDelete));
                pos = delPos + delimiter.length();
            }
            if (str.length() > 0 && pos <= str.length()) {
                // Add rest of String, but not in case of empty input.
                result.add(deleteAny(str.substring(pos), charsToDelete));
            }
        }
        return toStringArray(result);
    }

    /**
     * Convert a CSV list into an array of Strings.
     * 
     * @param str
     *            the input String
     * @return an array of Strings, or the empty array in case of empty input
     */
    public static String[] commaDelimitedListToStringArray(String str) {
        return delimitedListToStringArray(str, ",");
    }

    /**
     * Convenience method to convert a CSV string list to a set. Note that this
     * will suppress duplicates.
     * 
     * @param str
     *            the input String
     * @return a Set of String entries in the list
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static Set commaDelimitedListToSet(String str) {
        Set set = new TreeSet();
        String[] tokens = commaDelimitedListToStringArray(str);
        for (int i = 0; i < tokens.length; i++) {
            set.add(tokens[i]);
        }
        return set;
    }

    /**
     * Copy the given Collection into a String array. The Collection must
     * contain String elements only.
     * 
     * @param collection
     *            the Collection to copy
     * @return the String array (<code>null</code> if the passed-in
     *         Collection was <code>null</code>)
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static String[] toStringArray(Collection collection) {
        if (collection == null) {
            return null;
        }
        return (String[]) collection.toArray(new String[collection.size()]);
    }

    /**
     * Convenience method to return a Collection as a delimited (e.g. CSV)
     * String. E.g. useful for <code>toString()</code> implementations.
     * 
     * @param coll
     *            the Collection to display
     * @param delim
     *            the delimiter to use (probably a ",")
     * @param prefix
     *            the String to start each element with
     * @param suffix
     *            the String to end each element with
     * @return the delimited String
     */
    @SuppressWarnings({ "rawtypes" })
    public static String collectionToDelimitedString(Collection coll,
            String delim, String prefix, String suffix) {
        if (CollectionUtils.isEmpty(coll)) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        Iterator it = coll.iterator();
        while (it.hasNext()) {
            sb.append(prefix).append(it.next()).append(suffix);
            if (it.hasNext()) {
                sb.append(delim);
            }
        }
        return sb.toString();
    }

    /**
     * Convenience method to return a Collection as a delimited (e.g. CSV)
     * String. E.g. useful for <code>toString()</code> implementations.
     * 
     * @param coll
     *            the Collection to display
     * @param delim
     *            the delimiter to use (probably a ",")
     * @return the delimited String
     */
    @SuppressWarnings("rawtypes")
	public static String collectionToDelimitedString(Collection coll,
            String delim) {
        return collectionToDelimitedString(coll, delim, "", "");
    }

    /**
     * Convenience method to return a Collection as a CSV String. E.g. useful
     * for <code>toString()</code> implementations.
     * 
     * @param coll
     *            the Collection to display
     * @return the delimited String
     */
	public static String collectionToCommaDelimitedString(@SuppressWarnings("rawtypes") Collection coll) {
        return collectionToDelimitedString(coll, ",");
    }

    /**
     * Convenience method to return a String array as a delimited (e.g. CSV)
     * String. E.g. useful for <code>toString()</code> implementations.
     * 
     * @param arr
     *            the array to display
     * @param delim
     *            the delimiter to use (probably a ",")
     * @return the delimited String
     */
    public static String arrayToDelimitedString(Object[] arr, String delim) {
        if (ObjectUtils.isEmpty(arr)) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) {
                sb.append(delim);
            }
            sb.append(arr[i]);
        }
        return sb.toString();
    }

    /**
     * Convenience method to return a String array as a CSV String. E.g. useful
     * for <code>toString()</code> implementations.
     * 
     * @param arr
     *            the array to display
     * @return the delimited String
     */
    public static String arrayToCommaDelimitedString(Object[] arr) {
        return arrayToDelimitedString(arr, ",");
    }

    /**
     * Delete the prefix header <code>get</code> from the getter of property
     * name.
     * 
     * @param getter
     * @return String
     */
    public static String delPrefixGetter(String getter) {
        return delPrefixHeader(getter, "get", false);
    }

    /**
     * Delete the prefix header <code>get</code> from the getter of property
     * name.
     * 
     * @param getter
     * @param noS
     * @return String
     */
    public static String delPrefixGetter(String getter, boolean noS) {
        return delPrefixHeader(getter, "get", noS);
    }

    /**
     * Delete the prefix header from property name.
     * 
     * @param accor
     * @param header
     * @param noS
     * @return String
     */
    public static String delPrefixHeader(String accor, String header,
            boolean noS) {
        if (accor.startsWith(header)) {
            if (noS)
                return uncapitalize(accor.substring(header.length())).concat(
                        accor.endsWith("s") ? accor.substring(
                                header.length() + 1, accor.length() - 1)
                                : accor.substring(header.length() + 1));
            else
                return uncapitalize(accor.substring(header.length()));
        }
        return accor;
    }

    /**
     * Add the prefix header <code>get</code> into the property name.
     * 
     * @param getter
     * @return String
     */
    public static String addPrefixGetter(String getter) {
        return addPrefixHeader(getter, "get", false);
    }

    /**
     * Add the prefix header <code>get</code> into the property name.
     * 
     * @param getter
     * @param noS
     * @return String
     */
    public static String addPrefixGetter(String getter, boolean noS) {
        return addPrefixHeader(getter, "get", noS);
    }

    /**
     * Add the prefix header into property name.
     * 
     * @param accor
     * @param header
     * @param addS
     * @return String
     */
    public static String addPrefixHeader(String accor, String header,
            boolean addS) {
        if (!accor.startsWith(header)) {
            if (addS)
                return header.concat(capitalize(accor).concat(
                        accor.endsWith("s") ? "" : "s"));
            else
                return header.concat(capitalize(accor));
        }
        return accor;
    }

    /**
     * Get nested path name on the right of first delimiter.
     * 
     * @param PropertyName
     * @return String
     */
    public static String getRightName(String pn) {
        return getSubstring(pn, 1, 1, NESTED_DELIM);
    }

    /**
     * Get nested path name on the left of last class name.
     * 
     * @param PropertyName
     * @return String
     */
    public static String getLeftName(String pn) {
        return getSubstring(pn, 0, -1, NESTED_DELIM);
    }

    /**
     * Get property name on the right side of nested path name.
     * 
     * @param PropertyName
     * @return String
     */
    public static String getPropertyName(String pn) {
        return getSubstring(pn, 0, 1, NESTED_DELIM);
    }

    /**
     * Split string with substring method...
     * 
     * @param String
     * @param startIndex
     * @param tokenSign
     * @param spChar
     *            delimiter for nested reference.
     * @return String
     */
    public static String getSubstring(String str, int startIndex,
            int tokenSign, char spChar) {
        if (!hasLength(str))
            return str;
        int idx = 0;
        int lastDot = -1;
        int dot = str.indexOf(spChar);
        do {
            idx++;
            if (idx == startIndex)
                break;
            lastDot = dot;
            dot = str.indexOf(spChar, dot + 1);
        } while (dot > -1);
        if (tokenSign < 0) {
            if (dot > 0 && idx == startIndex)
                return str.substring(0, dot);
            if (dot == -1 && lastDot > 0)
                if (startIndex > 0)
                    return str;
                else
                    return str.substring(0, lastDot);
            return "";
        } else if (tokenSign > 0) {
            if (dot > -1 && dot < str.length() && idx == startIndex)
                return str.substring(dot + 1);
            if (dot == -1 && lastDot > -1 && lastDot < str.length())
                return str.substring(lastDot + 1);
            return "";
        } else {
            if (idx == startIndex - 1)
                return str.substring(lastDot + 1, str.length());
            if (idx != startIndex)
                return "";
            if (dot > 0 && lastDot == dot)
                return str.substring(0, dot);
            return str.substring(lastDot + 1, dot);
        }
    }

    /**
     * Parse integer into string with specified length. '0' will be inserted if
     * length is not enough.
     * 
     * @param value
     * @param len
     * @return String
     */
    public static String leftPadInteger(int value, int len) {
        return leftPadInteger(new Integer(value), len);
    }

    /**
     * Parse long into string with specified length. '0' will be inserted if
     * length is not enough.
     * 
     * @param value
     * @param len
     * @return String
     */
    public static String leftPadInteger(long value, int len) {
        return leftPadInteger(new Long(value), len);
    }

    /**
     * Parse number into string with specified length. '0' will be inserted if
     * length is not enough.
     * 
     * @param value
     * @param len
     * @return String
     */
    public static String leftPadInteger(Number value, int len) {
        if (null != value) {
            String t = String.valueOf(value);
            if (null != t) {
                int l = t.length();
                int pos = 0;
                if (value.longValue() < 0) {
                    l--;
                    pos = 1;
                }
                return l < len ? StringUtil.left(t, pos) + repeat("0", len - l)
                        + t.substring(pos) : t;
            }
        }
        return repeat("0", len);
    }

    /**
     * Convert a String to an boolean. Accepts: 1/0, yes/no, true/false - case
     * insensitive. If the value does not map to "true,", <code>false</code>
     * is returned.
     * 
     * @param in
     *            String to be parsed.
     * @return boolean representation of String.
     */
    public static boolean parseBoolean(String in) {
        in = defaultString(in);
        if (in.length() == 0) {
            return false;
        }
        switch (in.charAt(0)) {
        case '1':
        case 'y':
        case 'Y':
        case 't':
        case 'T':
            return true;
        }
        return false;
    }

    /**
     * Make a new string by duplicating string for some times.
     * 
     * @param str
     *            String to be duplicated
     * @param count
     * @return String
     */
    public static String duplicate(String str, int count) {
        if (isEmpty(str) || count < 0)
            return str;
        StringBuffer sb = new StringBuffer(str.length() * count);
        for (int i = 0; i < count; i++) {
            sb.append(str);
        }
        return sb.toString();
    }

    /**
     * A null-safe <code>toString()</code> method.
     * 
     * @param Object
     * @return null will be returned if object is null.
     */
    public static String toString(Object o) {
        return null == o ? null : o.toString();
    }

    /**
     * Simple reflection <code>toString()</code> method, more facility see
     * also <code>ToStringBuilder</code> class.
     * 
     * @see org.apache.commons.lang.builder.ReflectionToStringBuilder
     * @param object
     * @return String
     */
    @SuppressWarnings("rawtypes")
	public static String reflectionToString(Object object) {
        if (object == null)
            return "<null>";
        if (object instanceof String) {
            return (String) object;
        } else if (object instanceof Object[]) {
            StringBuffer sb = new StringBuffer("[");
            for (int i = 0; i < ((Object[]) object).length; i++) {
                Object o = ((Object[]) object)[i];
                sb.append(reflectionToString(o));
                if (i < ((Object[]) object).length - 1)
                    sb.append(ARRAY_DELIM).append(CARRAY_LINEFEED);
            }
            sb.append("]");
            return sb.toString();
        } else if (object instanceof Collection) {
            StringBuffer sb = new StringBuffer("[");
            for (Iterator it = ((Collection) object).iterator(); it.hasNext();) {
                Object o = it.next();
                sb.append(reflectionToString(o));
                if (it.hasNext())
                    sb.append(ARRAY_DELIM).append(CARRAY_LINEFEED);
            }
            sb.append("]");
            return sb.toString();
        } else if (object instanceof Map) {
            StringBuffer sb = new StringBuffer("{");
            for (Iterator it = ((Map) object).keySet().iterator(); it.hasNext();) {
                Object key = it.next();
                Object value = ((Map) object).get(key);
                sb.append(reflectionToString(key));
                sb.append(KEYVALUE_DELIM);
                sb.append(reflectionToString(value));
                if (it.hasNext())
                    sb.append(ARRAY_DELIM).append(CARRAY_LINEFEED);
            }
            sb.append("}");
            return sb.toString();
        } else if (object instanceof int[]) {
            StringBuffer sb = new StringBuffer("[");
            for (int i = 0; i < ((int[]) object).length; i++) {
                int o = ((int[]) object)[i];
                sb.append(o);
                if (i < ((int[]) object).length - 1)
                    sb.append(ARRAY_DELIM);
            }
            sb.append("]");
            return sb.toString();
        } else if (object instanceof long[]) {
            StringBuffer sb = new StringBuffer("[");
            for (int i = 0; i < ((long[]) object).length; i++) {
                long o = ((long[]) object)[i];
                sb.append(o);
                if (i < ((long[]) object).length - 1)
                    sb.append(ARRAY_DELIM);
            }
            sb.append("]");
            return sb.toString();
        } else if (object instanceof float[]) {
            StringBuffer sb = new StringBuffer("[");
            for (int i = 0; i < ((float[]) object).length; i++) {
                float o = ((float[]) object)[i];
                sb.append(o);
                if (i < ((float[]) object).length - 1)
                    sb.append(ARRAY_DELIM);
            }
            sb.append("]");
            return sb.toString();
        } else if (object instanceof double[]) {
            StringBuffer sb = new StringBuffer("[");
            for (int i = 0; i < ((double[]) object).length; i++) {
                double o = ((double[]) object)[i];
                sb.append(o);
                if (i < ((double[]) object).length - 1)
                    sb.append(ARRAY_DELIM);
            }
            sb.append("]");
            return sb.toString();
        }
        return ReflectionToStringBuilder.reflectionToString(object);
    }

    /**
     * Null-safe and convenience one parameter
     * <code>MessageFormat.format(String, Object[])</code> method.
     * 
     * @see java.text.MessageFormat
     * @param pattern
     * @param arg0
     * @return Formatted String
     */
    public static String format(String pattern, int arg0) {
        return format(pattern, new Integer(arg0));
    }

    /**
     * Null-safe and convenience one parameter
     * <code>MessageFormat.formatMessageFormat.format(String, Object[])</code>
     * method.
     * 
     * @see java.text.MessageFormat
     * @param pattern
     * @param arg0
     * @return Formatted String
     */
    public static String format(String pattern, long arg0) {
        return format(pattern, new Long(arg0));
    }

    /**
     * Null-safe and convenience one parameter
     * <code>MessageFormat.format(String, Object[])</code> method, use default
     * locale.
     * 
     * @see java.text.MessageFormat
     * @param pattern
     * @param arg0
     * @return Formatted String
     */
    public static String format(String pattern, Object arg0) {
        return format(pattern, (arg0 instanceof Object[]) ? (Object[]) arg0
                : new Object[] { arg0 }, null);
    }

    /**
     * Null-safe <code>MessageFormat.format(String, Object[])</code> method,
     * locale used for formating currency/number/date.
     * 
     * @see java.text.MessageFormat
     * @param pattern
     * @param arguments
     * @param locale
     * @return Formatted String
     */
    public static String format(String pattern, Object[] arguments,
            Locale locale) {
        if (null == pattern)
            return null;
        MessageFormat temp = new MessageFormat(pattern);
        if (null != locale)
            temp.setLocale(locale);
        return temp.format(arguments);
    }

    /**
     * Null-safe <code>String.matches(String)</code> method.
     * 
     * @param str
     * @param regex
     * @return true if string matches the regular exp or they are both null.
     */
    public static boolean matches(String str, String regex) {
        if (null == str)
            return null == regex;
        return str.matches(regex);
    }

    /**
     * Parse the given String value recursively, to be able to resolve nested
     * placeholders (when resolved property values in turn contain placeholders
     * again). The default placeholder syntax follows the Ant / Log4J / JSP EL
     * style.
     * 
     * @param strVal
     *            the String value to parse
     * @param props
     *            the Properties to resolve placeholders against
     * @throws IllegalArgumentException
     *             if invalid values are encountered
     * @see #parseStringValue(String, Properties, String, String, String)
     * @see org.springframework.beans.factory.config.PropertyPlaceholderConfigurer
     */
    public static String parseStringValue(String strVal, Properties props) {
        return parseStringValue(strVal, props, null, null, null);
    }

    /**
     * Parse the given String value recursively, to be able to resolve nested
     * placeholders (when resolved property values in turn contain placeholders
     * again). The default placeholder syntax follows the Ant / Log4J / JSP EL
     * style, and can be customized with prefix and suffix.
     * 
     * @param strVal
     *            the String value to parse
     * @param props
     *            the Properties to resolve placeholders against
     * @param originalPlaceholder
     *            the original placeholder, used to detect circular references
     *            between placeholders. Only non-null if we're parsing a nested
     *            placeholder.
     * @param placeholderPrefix
     *            the String for placeholder prefix, '${' by default.
     * @param placeholderSuffix
     *            the String for placeholder suffix, '}' by default.
     * @throws IllegalArgumentException
     *             if invalid values are encountered
     * @see org.springframework.beans.factory.config.PropertyPlaceholderConfigurer
     */
    public static String parseStringValue(String strVal, Properties props,
            String originalPlaceholder, String placeholderPrefix,
            String placeholderSuffix) {
        if (null == strVal)
            return null;
        placeholderPrefix = defaultIfEmpty(placeholderPrefix,
                PLACEHOLDER_PREFIX);
        placeholderSuffix = defaultIfEmpty(placeholderSuffix,
                PLACEHOLDER_SUFFIX);
        StringBuffer buf = new StringBuffer(strVal);
        int startIndex = buf.indexOf(placeholderPrefix);
        while (startIndex != -1) {
            int endIndex = buf.toString().indexOf(placeholderSuffix,
                    startIndex + placeholderPrefix.length());
            if (endIndex != -1) {
                String placeholder = buf.substring(startIndex
                        + placeholderPrefix.length(), endIndex);
                String originalPlaceholderToUse = null;

                if (originalPlaceholder != null) {
                    originalPlaceholderToUse = originalPlaceholder;
                    if (placeholder.equals(originalPlaceholder)) {
                        throw new IllegalArgumentException(
                                "Circular placeholder reference '"
                                        + placeholder
                                        + "' in property definitions");
                    }
                } else {
                    originalPlaceholderToUse = placeholder;
                }

                String propVal = props.getProperty(placeholder);
                if (propVal == null) {
                    propVal = System.getProperty(placeholder);
                }
                if (propVal != null) {
                    propVal = parseStringValue(propVal, props,
                            originalPlaceholderToUse, placeholderPrefix,
                            placeholderSuffix);
                    buf.replace(startIndex, endIndex
                            + placeholderSuffix.length(), propVal);
                    startIndex = buf.toString().indexOf(placeholderPrefix,
                            startIndex + propVal.length());
                } else {
                    startIndex = buf.toString().indexOf(placeholderPrefix,
                            endIndex + placeholderSuffix.length());
                }
            } else {
                startIndex = -1;
            }
        }
        return buf.toString();
    }
    
    /**
     * 判断字符串是否为空
     */
    public static boolean isEmpty(String str) {
        if (str != null) {
            str = str.trim();
        }
        return StringUtils.isEmpty(str);
    }

}
