package com.mathieuclement.lib.chlaw.parser;

import com.mathieuclement.lib.chlaw.util.LocalizedString;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

public interface ILawParser {
    /**
     * Returns the fields from the "additional information" panel on the right
     * of the Law page. Example of data:  "Abréviation" => "CP".
     *
     * @return the fields from the "additional information" panel on the right
     * of the Law page
     */
    Map<LocalizedString, LocalizedString> getAdditionalInformation();

    /**
     * Returns the identifier of the law, suc as "311.0"
     * @return the identifier of the law, suc as "311.0"
     */
    LocalizedString getLawId();

    /**
     * Returns the full title of the document, such as
     * "Code pénal suisse du 21 décembre 1937"
     * @return the full title of the document
     */
    LocalizedString getLongTitle();

    /**
     * Returns the short title of the document, such as
     * "Code pénal suisse"
     * @return
     */
    LocalizedString getShortTitle();

    /**
     * Returns a shortcut to additional information => Abbreviation
     * if it exists
     * @return the law abbreviation, if any or null.
     */
    LocalizedString getAbreviation();

    /**
     * Returns date of last update, such as "1<sup>er</sup> janvier 2014"
     * @return date of last update
     */
    // Date object allows to use locale-dependent date format in the UI
    Date getStateDate() throws ParseException;



}
