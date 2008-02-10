package uebungen.ueb01;
import java.io.*;
import java.util.*;

/**
 *    <H1>Die Toolbox : <B>de.htw.saarland.stl.Stdin</B></H1>
 *    <BR><BR>  Stellt für die Datentypen :
 *    <UL><LI>   float
 *        <LI>   double
 *        <LI>   byte
 *        <LI>   short
 *        <LI>   int
 *        <LI>   long
 *        <LI>   boolean
 *        <LI>   char
 *        <LI>   String
 *    </UL> 
 *    Einlese-Methoden <B>readXX</B> und <B>readlnXX</B> von
 *    der Standard-Eingabe zur Verfügung
 *
 *
 * @version     1.0 03.Dezember 2001
 * @author      Wolfgang Pauly
 * @see         <A HREF="./StdinTest.java"> Beispielprogramm </A>
 */

public class Stdin
 {
             private static StreamTokenizer teiler;
             private static boolean open = false;
             //private static boolean eolnUeberlesen = false;
             private static boolean readMerker = false;



/**           <UL><LI> liest einen von white-spaces begrenzten String von der 
 *                     Standardeingabe ein und wandelt ihn
 *                     in eine float-Zahl um.
 *
 *            <LI>     der Lesezeiger bleibt hinter den eingelesenen Zeichen
 *                     in der aktuellen Zeile stehen.
 *            </UL><BR>
 *            @return die eingelesene Float-Zahl
 *            @exception NumberFormatException falls Eingabe KEINE Zahl oder ausserhalb des Wertebereiches
 */

  public static float readFloat()
     {
            Double gelesen;

      gelesen = uebertrageNumberToken();
      werteBereichsUeberpruefung( gelesen, 'f' );
      return gelesen.floatValue();
     }

/**           <UL><LI> gibt den Eingabe-Aufforderungs-String <CODE>prompt</CODE> aus und
 *                     liest danach einen von white-spaces begrenzten String von der 
 *                     Standardeingabe ein und wandelt ihn
 *                     in eine float-Zahl um.
 *
 *            <LI>     der Lesezeiger bleibt hinter den eingelesenen Zeichen
 *                     in der aktuellen Zeile stehen.
 *            </UL><BR>
 *            @param  prompt ist der Eingabe-Aufforderungs-String
 *            @return die eingelesene Float-Zahl
 *            @exception NumberFormatException falls Eingabe KEINE Zahl oder ausserhalb des Wertebereiches
 */

  public static float readFloat( String prompt )
     {
      printPrompt ( prompt );
      return readFloat();
     }


/**           <UL><LI> liest einen von white-spaces begrenzten String von der 
 *                     Standardeingabe ein und wandelt ihn
 *                     in eine float-Zahl um.
 *
 *            <LI>     der Lesezeiger wird hinter das EOLN der aktuellen Zeile
 *                     gestellt, d.h. der Rest der Eingabezeile wird ueberlesen.
 *            </UL><BR>     
 *            @return die eingelesene Float-Zahl
 *            @exception NumberFormatException falls Eingabe KEINE Zahl oder ausserhalb des Wertebereiches
 */

  public static float readlnFloat()
     {
            Double gelesen;

      gelesen = uebertrageNumberZeile();
      werteBereichsUeberpruefung( gelesen, 'f' );
      return gelesen.floatValue();
     }

/**           <UL><LI> gibt den Eingabe-Aufforderungs-String <CODE>prompt</CODE> aus und
 *                     liest danach einen von white-spaces begrenzten String von der 
 *                     Standardeingabe ein und wandelt ihn
 *                     in eine float-Zahl um.
 *
 *            <LI>     der Lesezeiger wird hinter das EOLN der aktuellen Zeile
 *                     gestellt, d.h. der Rest der Eingabezeile wird ueberlesen.
 *            </UL><BR>     
 *            @param  prompt ist der Eingabe-Aufforderungs-String
 *            @return die eingelesene Float-Zahl
 *            @exception NumberFormatException falls Eingabe KEINE Zahl oder ausserhalb des Wertebereiches
 */

  public static float readlnFloat( String prompt )
     {
      printPrompt ( prompt );
      return readlnFloat();
     }




/**           <UL><LI> liest einen von white-spaces begrenzten String von der 
 *                     Standardeingabe ein und wandelt ihn
 *                     in eine double-Zahl um.
 *
 *            <LI>     der Lesezeiger bleibt hinter den eingelesenen Zeichen
 *                     in der aktuellen Zeile stehen.
 *            </UL><BR> 
 *            @return die eingelesene Double-Zahl
 *            @exception NumberFormatException falls Eingabe KEINE Zahl oder ausserhalb des Wertebereiches
 */

  public static double readDouble()
     {
            Double gelesen;

      gelesen = uebertrageNumberToken();
      return gelesen.doubleValue();
     }

/**           <UL><LI> gibt den Eingabe-Aufforderungs-String <CODE>prompt</CODE> aus und
 *                     liest danach einen von white-spaces begrenzten String von der 
 *                     Standardeingabe ein und wandelt ihn
 *                     in eine double-Zahl um.
 *
 *            <LI>     der Lesezeiger bleibt hinter den eingelesenen Zeichen
 *                     in der aktuellen Zeile stehen.
 *
 *            </UL><BR> 
 *            @param  prompt ist der Eingabe-Aufforderungs-String
 *            @return die eingelesene Double-Zahl
 *            @exception NumberFormatException falls Eingabe KEINE Zahl oder ausserhalb des Wertebereiches
 */

  public static double readDouble( String prompt )
     {
      printPrompt ( prompt );
      return readDouble();
     }



/**           <UL><LI> liest einen von white-spaces begrenzten String von der 
 *                     Standardeingabe ein und wandelt ihn
 *                     in eine double-Zahl um.
 *
 *            <LI>     der Lesezeiger wird hinter das EOLN der aktuellen Zeile
 *                     gestellt, d.h. der Rest der Eingabezeile wird ueberlesen.
 *
 *            </UL><BR> 
 *            @return die eingelesene Double-Zahl
 *            @exception NumberFormatException falls Eingabe KEINE Zahl oder ausserhalb des Wertebereiches
 */

  public static double readlnDouble()
     {
            Double gelesen = new Double( 0.0 );

      try
        {
            gelesen = uebertrageNumberZeile();
        }
      catch ( NumberFormatException e )
        {
         String str = new String( e.toString() );
         int    pos1 = str.indexOf(':') + 1;
         int    pos2 = str.indexOf(':', pos1) + 1;
         erzeugeNumberFormatException( str.substring( pos2 ), 'd' );
        }
      return gelesen.doubleValue();
     }

/**           <UL><LI> gibt den Eingabe-Aufforderungs-String <CODE>prompt</CODE> aus und
 *                     liest danach einen von white-spaces begrenzten String von der 
 *                     Standardeingabe ein und wandelt ihn
 *                     in eine double-Zahl um.
 *
 *            <LI>     der Lesezeiger wird hinter das EOLN der aktuellen Zeile
 *                     gestellt, d.h. der Rest der Eingabezeile wird ueberlesen.
 *
 *            </UL><BR> 
 *            @param  prompt ist der Eingabe-Aufforderungs-String
 *            @return die eingelesene Double-Zahl
 *            @exception NumberFormatException falls Eingabe KEINE Zahl oder ausserhalb des Wertebereiches
 */

  public static double readlnDouble( String prompt )
     {
      printPrompt ( prompt );
      return readlnDouble();
     }


/**           <UL><LI> liest einen von white-spaces begrenzten String von der 
 *                     Standardeingabe ein und wandelt ihn
 *                     in eine byte-Zahl um.
 *
 *            <LI>     der Lesezeiger bleibt hinter den eingelesenen Zeichen
 *                     in der aktuellen Zeile stehen.
 *
 *            </UL><BR> 
 *            @return die eingelesene Byte-Zahl
 *            @exception NumberFormatException falls Eingabe KEINE Zahl oder ausserhalb des Wertebereiches
 */

  public static byte readByte()
     {
            Double gelesen;

      gelesen = uebertrageNumberToken();
      if ( gelesen.doubleValue() != gelesen.byteValue() )
        {
         werteBereichsUeberpruefung( gelesen, 'b' );
         erzeugeNumberFormatException( gelesen.toString(), 'b' );
        }
      return gelesen.byteValue();
     }

/**           <UL><LI> gibt den Eingabe-Aufforderungs-String <CODE>prompt</CODE> aus und
 *                     liest danach einen von white-spaces begrenzten String von der 
 *                     Standardeingabe ein und wandelt ihn
 *                     in eine byte-Zahl um.
 *
 *            <LI>     der Lesezeiger bleibt hinter den eingelesenen Zeichen
 *                     in der aktuellen Zeile stehen.
 *
 *            </UL><BR> 
 *            @param  prompt ist der Eingabe-Aufforderungs-String
 *            @return die eingelesene Byte-Zahl
 *            @exception NumberFormatException falls Eingabe KEINE Zahl oder ausserhalb des Wertebereiches
 */

  public static byte readByte( String prompt )
     {
      printPrompt ( prompt );
      return readByte();
     }



/**           <UL><LI> liest einen von white-spaces begrenzten String von der 
 *                     Standardeingabe ein und wandelt ihn
 *                     in eine byte-Zahl um.
 *
 *            <LI>     der Lesezeiger wird hinter das EOLN der aktuellen Zeile
 *                     gestellt, d.h. der Rest der Eingabezeile wird ueberlesen.
 *
 *            </UL><BR> 
 *            @return die eingelesene Byte-Zahl
 *            @exception NumberFormatException falls Eingabe KEINE Zahl oder ausserhalb des Wertebereiches
 */

  public static byte readlnByte()
     {
            Double gelesen;

      gelesen = uebertrageNumberZeile();
      if ( gelesen.doubleValue() != gelesen.byteValue() )
        {
         werteBereichsUeberpruefung( gelesen, 'b' );
         erzeugeNumberFormatException( gelesen.toString(), 'b' );
        }
      return gelesen.byteValue();
     }

/**           <UL><LI> gibt den Eingabe-Aufforderungs-String <CODE>prompt</CODE> aus und
 *                     liest danach einen von white-spaces begrenzten String von der 
 *                     Standardeingabe ein und wandelt ihn
 *                     in eine byte-Zahl um.
 *
 *            <LI>     der Lesezeiger bleibt hinter den eingelesenen Zeichen
 *                     in der aktuellen Zeile stehen.
 *
 *            </UL><BR> 
 *            @param  prompt ist der Eingabe-Aufforderungs-String
 *            @return die eingelesene Byte-Zahl
 *            @exception NumberFormatException falls Eingabe KEINE Zahl oder ausserhalb des Wertebereiches

 */

  public static byte readlnByte( String prompt )
     {
      printPrompt ( prompt );
      return readlnByte();
     }



/**           <UL><LI> liest einen von white-spaces begrenzten String von der 
 *                     Standardeingabe ein und wandelt ihn
 *                     in eine short-Zahl um.
 *
 *            <LI>     der Lesezeiger bleibt hinter den eingelesenen Zeichen
 *                     in der aktuellen Zeile stehen.
 *
 *            </UL><BR> 
 *            @return die eingelesene Short-Zahl
 *            @exception NumberFormatException falls Eingabe KEINE Zahl oder ausserhalb des Wertebereiches
 */

  public static short readShort()
     {
            Double gelesen;

      gelesen = uebertrageNumberToken();
      if ( gelesen.doubleValue() != gelesen.shortValue() )
        {
         werteBereichsUeberpruefung( gelesen, 's' );
         erzeugeNumberFormatException( gelesen.toString(), 's' );
        }
      return gelesen.shortValue();
     }

/**           <UL><LI> gibt den Eingabe-Aufforderungs-String <CODE>prompt</CODE> aus und
 *                     liest danach einen von white-spaces begrenzten String von der 
 *                     Standardeingabe ein und wandelt ihn
 *                     in eine short-Zahl um.
 *
 *            <LI>     der Lesezeiger bleibt hinter den eingelesenen Zeichen
 *                     in der aktuellen Zeile stehen.
 *
 *            </UL><BR> 
 *            @param  prompt ist der Eingabe-Aufforderungs-String
 *            @return die eingelesene Short-Zahl
 *            @exception NumberFormatException falls Eingabe KEINE Zahl oder ausserhalb des Wertebereiches
 */

  public static short readShort( String prompt )
     {
      printPrompt ( prompt );
      return readShort();
     }


/**           <UL><LI> liest einen von white-spaces begrenzten String von der 
 *                     Standardeingabe ein und wandelt ihn
 *                     in eine short-Zahl um.
 *
 *            <LI>     der Lesezeiger wird hinter das EOLN der aktuellen Zeile
 *                     gestellt, d.h. der Rest der Eingabezeile wird ueberlesen.
 *
 *            </UL><BR> 
 *            @return die eingelesene Short-Zahl
 *            @exception NumberFormatException falls Eingabe KEINE Zahl oder ausserhalb des Wertebereiches
 */

  public static short readlnShort()
     {
            Double gelesen;

      gelesen = uebertrageNumberZeile();
      if ( gelesen.doubleValue() != gelesen.shortValue() )
        {
         werteBereichsUeberpruefung( gelesen, 's' );
         erzeugeNumberFormatException( gelesen.toString(), 's' );
        }
      return gelesen.shortValue();
     }

/**           <UL><LI> gibt den Eingabe-Aufforderungs-String <CODE>prompt</CODE> aus und
 *                     liest danach einen von white-spaces begrenzten String von der 
 *                     Standardeingabe ein und wandelt ihn
 *                     in eine short-Zahl um.
 *
 *            <LI>     der Lesezeiger wird hinter das EOLN der aktuellen Zeile
 *                     gestellt, d.h. der Rest der Eingabezeile wird ueberlesen.
 *
 *            </UL><BR> 
 *            @param  prompt ist der Eingabe-Aufforderungs-String
 *            @return die eingelesene Short-Zahl
 *            @exception NumberFormatException falls Eingabe KEINE Zahl oder ausserhalb des Wertebereiches
 */

  public static short readlnShort( String prompt )
     {
      printPrompt ( prompt );
      return readlnShort();
     }



/**           <UL><LI> liest einen von white-spaces begrenzten String von der 
 *                     Standardeingabe ein und wandelt ihn
 *                     in eine int-Zahl um.
 *
 *            <LI>     der Lesezeiger bleibt hinter den eingelesenen Zeichen
 *                     in der aktuellen Zeile stehen.
 *
 *            </UL><BR> 
 *            @return die eingelesene Int-Zahl
 *            @exception NumberFormatException falls Eingabe KEINE Zahl oder ausserhalb des Wertebereiches
 */

  public static int readInt()
     {
            Double gelesen;

      gelesen = uebertrageNumberToken();
      if ( gelesen.doubleValue() != gelesen.intValue() )
        {
         werteBereichsUeberpruefung( gelesen, 'i' );
         erzeugeNumberFormatException( gelesen.toString(), 'i' );
        }
      return gelesen.intValue();
     }

/**           <UL><LI> gibt den Eingabe-Aufforderungs-String <CODE>prompt</CODE> aus und
 *                     liest danach einen von white-spaces begrenzten String von der 
 *                     Standardeingabe ein und wandelt ihn
 *                     in eine int-Zahl um.
 *
 *            <LI>     der Lesezeiger bleibt hinter den eingelesenen Zeichen
 *                     in der aktuellen Zeile stehen.
 *
 *            </UL><BR> 
 *            @param  prompt ist der Eingabe-Aufforderungs-String
 *            @return die eingelesene Int-Zahl
 *            @exception NumberFormatException falls Eingabe KEINE Zahl oder ausserhalb des Wertebereiches
 */

  public static int readInt( String prompt )
     {
      printPrompt ( prompt );
      return readInt();
     }


/**           <UL><LI> liest einen von white-spaces begrenzten String von der 
 *                     Standardeingabe ein und wandelt ihn
 *                     in eine int-Zahl um.
 *
 *            <LI>     der Lesezeiger wird hinter das EOLN der aktuellen Zeile
 *                     gestellt, d.h. der Rest der Eingabezeile wird ueberlesen.
 *
 *            </UL><BR> 
 *            @return die eingelesene Int-Zahl
 *            @exception NumberFormatException falls Eingabe KEINE Zahl oder ausserhalb des Wertebereiches
 */

  public static int readlnInt()
     {
            Double gelesen;

      gelesen = uebertrageNumberZeile();
      if ( gelesen.doubleValue() != gelesen.intValue() )
        {
         werteBereichsUeberpruefung( gelesen, 'i' );
         erzeugeNumberFormatException( gelesen.toString(), 'i' );
        }
      return gelesen.intValue();
     }

/**           <UL><LI> gibt den Eingabe-Aufforderungs-String <CODE>prompt</CODE> aus und
 *                     liest danach einen von white-spaces begrenzten String von der 
 *                     Standardeingabe ein und wandelt ihn
 *                     in eine int-Zahl um.
 *
 *            <LI>     der Lesezeiger wird hinter das EOLN der aktuellen Zeile
 *                     gestellt, d.h. der Rest der Eingabezeile wird ueberlesen.
 *
 *            </UL><BR> 
 *            @param  prompt ist der Eingabe-Aufforderungs-String
 *            @return die eingelesene Int-Zahl
 *            @exception NumberFormatException falls Eingabe KEINE Zahl oder ausserhalb des Wertebereiches
 */

  public static int readlnInt( String prompt )
     {
      printPrompt ( prompt );
      return readlnInt();
     }



/**           <UL><LI> liest einen von white-spaces begrenzten String von der 
 *                     Standardeingabe ein und wandelt ihn
 *                     in eine long-Zahl um.
 *
 *            <LI>     der Lesezeiger bleibt hinter den eingelesenen Zeichen
 *                     in der aktuellen Zeile stehen.
 *
 *            </UL><BR> 
 *            @return die eingelesene Long-Zahl
 *            @exception NumberFormatException falls Eingabe KEINE Zahl oder ausserhalb des Wertebereiches
 */

  public static long readLong()
     {
            Double gelesen;

      gelesen = uebertrageNumberToken();
      if ( gelesen.doubleValue() != gelesen.longValue() )
        {
         werteBereichsUeberpruefung( gelesen, 'l' );
         erzeugeNumberFormatException( gelesen.toString(), 'l' );
        }
      return gelesen.longValue();
     }

/**           <UL><LI> gibt den Eingabe-Aufforderungs-String <CODE>prompt</CODE> aus und
 *                     liest danach einen von white-spaces begrenzten String von der 
 *                     Standardeingabe ein und wandelt ihn
 *                     in eine long-Zahl um.
 *
 *            <LI>     der Lesezeiger bleibt hinter den eingelesenen Zeichen
 *                     in der aktuellen Zeile stehen.
 *
 *            </UL><BR> 
 *            @param  prompt ist der Eingabe-Aufforderungs-String
 *            @return die eingelesene Long-Zahl
 *            @exception NumberFormatException falls Eingabe KEINE Zahl oder auss
erhalb des Wertebereiches
 */

  public static long readLong( String prompt )
     {
      printPrompt ( prompt );
      return readLong();
     }




/**           <UL><LI> liest einen von white-spaces begrenzten String von der 
 *                     Standardeingabe ein und wandelt ihn
 *                     in eine long-Zahl um.
 *
 *            <LI>     der Lesezeiger wird hinter das EOLN der aktuellen Zeile
 *                     gestellt, d.h. der Rest der Eingabezeile wird ueberlesen.
 *
 *            </UL><BR> 
 *            @return die eingelesene Long-Zahl
 *            @exception NumberFormatException falls Eingabe KEINE Zahl oder ausserhalb des Wertebereiches
 */

  public static long readlnLong()
     {
            Double gelesen;

      gelesen = uebertrageNumberZeile();
      if ( gelesen.doubleValue() != gelesen.longValue() )
        {
         werteBereichsUeberpruefung( gelesen, 'l' );
         erzeugeNumberFormatException( gelesen.toString(), 'l' );
        }
      return gelesen.longValue();
     }

/**           <UL><LI> gibt den Eingabe-Aufforderungs-String <CODE>prompt</CODE> aus und
 *                     liest danach einen von white-spaces begrenzten String von der 
 *                     Standardeingabe ein und wandelt ihn
 *                     in eine long-Zahl um.
 *
 *            <LI>     der Lesezeiger wird hinter das EOLN der aktuellen Zeile
 *                     gestellt, d.h. der Rest der Eingabezeile wird ueberlesen.
 *
 *            </UL><BR> 
 *            @param  prompt ist der Eingabe-Aufforderungs-String
 *            @return die eingelesene Long-Zahl
 *            @exception NumberFormatException falls Eingabe KEINE Zahl oder ausserhalb des Wertebereiches
 */

  public static long readlnLong( String prompt )
     {
      printPrompt ( prompt );
      return readlnLong();
     }



/**           <UL><LI> liest einen von white-spaces begrenzten String von der
 *                     Standardeingabe ein.
 *
 *            <LI>     der Lesezeiger bleibt hinter den eingelesenen Zeichen
 *                     in der aktuellen Zeile stehen.
 *
 *            </UL><BR> 
 *            @return den eingelesenen String
 */

  public static String readString()
     {
            String gelesen;

      initTokenstrom();
      setzeTokenstromMerkmale( 33 );
      leseToken();
      gelesen = uebertrageStringToken();
      zuruecksetzenTokenstromMerkmale();
      return gelesen;
     }
 
/**           <UL><LI> gibt den Eingabe-Aufforderungs-String <CODE>prompt</CODE> aus und
 *                     liest danach einen von white-spaces begrenzten String von der
 *                     Standardeingabe ein.
 *
 *            <LI>     der Lesezeiger bleibt hinter den eingelesenen Zeichen
 *                     in der aktuellen Zeile stehen.
 *
 *            </UL><BR> 
 *            @param  prompt ist der Eingabe-Aufforderungs-String
 *            @return den eingelesenen String
 */

  public static String readString( String prompt )
     {
      printPrompt ( prompt );
      return readString();
     }


/**           <UL><LI> liest eine komplette Zeile von der Standardeingabe ein.
 *
 *            <LI>     der Lesezeiger wird hinter das EOLN der aktuellen Zeile
 *                     gestellt.
 *
 *            </UL><BR> 
 *            @return den eingelesenen String
 */

  public static String readlnString()
     {
            StringBuffer gelesen = new StringBuffer();
            String       helfer;
            int    zeilenMerker, zeileAktuell;

      initTokenstrom();
      setzeTokenstromMerkmale( 32 );

      try
        {
         zeilenMerker = teiler.lineno();
         if ( ( teiler.ttype == teiler.TT_EOL ) ||
              ( teiler.sval  == null )          ||
              readMerker
            )
           {
            teiler.nextToken();
            while ( readMerker && ( teiler.ttype == teiler.TT_EOL ) )
              {
               zeilenMerker = teiler.lineno();
               teiler.nextToken();
              }
           };
         zeileAktuell = teiler.lineno();
         while ( (zeilenMerker == zeileAktuell) && 
                 (teiler.ttype != teiler.TT_EOL) &&
                 (teiler.ttype != teiler.TT_EOF)
               )
           {
            helfer = new String( teiler.toString());
            gelesen.append( helfer.substring( helfer.indexOf('[') + 1, 
                                              helfer.lastIndexOf(']')
                                            )
                          )
                   .append( '\t' );
            teiler.nextToken();
            zeileAktuell = teiler.lineno();
           }
          readMerker = false;
        }
      catch (Exception e)
        {
          System.out.println("\n\nKeine Daten Error: " + e.getMessage() );
        }


      zuruecksetzenTokenstromMerkmale();

      helfer = gelesen.toString();
      if ( helfer.length() == 0 )
        {
         if ( teiler.ttype == teiler.TT_EOF )
           {
            return null;
           }
         else
           {
            return helfer;
           }
        }
      else
        {
         return helfer.substring( 0,helfer.length()-1);
        }
     }

/**           <UL><LI> gibt den Eingabe-Aufforderungs-String <CODE>prompt</CODE> aus und
 *                     liest danach eine komplette Zeile von der Standardeingabe ein.
 *
 *            <LI>     der Lesezeiger wird hinter das EOLN der aktuellen Zeile
 *                     gestellt.
 *
 *            </UL><BR> 
 *            @param  prompt ist der Eingabe-Aufforderungs-String
 *            @return den eingelesenen String
 */

  public static String readlnString( String prompt )
     {
      printPrompt ( prompt );
      return readlnString();
     }



/**           <UL><LI> liest einen von white-spaces begrenzten String, der die
 *                     Zeichenfolgen "true, True, T, t, false, False, F, f"
 *                     enthalten darf von der Standardeingabe ein.
 *
 *            <LI>     der Lesezeiger bleibt hinter den eingelesenen Zeichen
 *                     in der aktuellen Zeile stehen.
 *
 *            </UL><BR> 
 *            @return den eingelesenen Boolean-Wert
 *            @exception RuntimeException falls Eingabe KEIN Booleanwert wir True || true || T || t || False || false || F || f ist.
 */

  public static boolean readBoolean()
     {
            String gelesen;
            boolean b_wert;

      initTokenstrom();
      setzeTokenstromMerkmale( 33 );
      leseToken();
      gelesen = uebertrageStringToken();
      b_wert  = uebertrageBooleanToken( gelesen );
      zuruecksetzenTokenstromMerkmale();
      return b_wert;
     }

/**           <UL><LI> gibt den Eingabe-Aufforderungs-String <CODE>prompt</CODE> aus und
 *                     liest danach einen von white-spaces begrenzten String, der die
 *                     Zeichenfolgen "true, True, T, t, false, False, F, f"
 *                     enthalten darf von der Standardeingabe ein.
 *
 *            <LI>     der Lesezeiger bleibt hinter den eingelesenen Zeichen
 *                     in der aktuellen Zeile stehen.
 *
 *            </UL><BR> 
 *            @param  prompt ist der Eingabe-Aufforderungs-String
 *            @return den eingelesenen Boolean-Wert
 *            @exception RuntimeException falls Eingabe KEIN Booleanwert wir True|| true || T || t || False || false || F || f ist.
 */

  public static boolean readBoolean( String prompt )
     {
      printPrompt ( prompt );
      return readBoolean();
     }


/**           <UL><LI> liest einen von white-spaces begrenzten String, der die
 *                     Zeichenfolgen "true, True, T, t, false, False, F, f"
 *                     enthalten darf von der Standardeingabe ein.
 *
 *            <LI>     der Lesezeiger wird hinter das EOLN der aktuellen Zeile
 *                     gestellt, d.h. der Rest der Eingabezeile wird ueberlesen.
 *
 *            </UL><BR> 
 *            @return den eingelesenen Boolean-Wert
 *            @exception RuntimeException falls Eingabe KEIN Booleanwert wir True || true || T || t || False || false || F || f ist.
 */

  public static boolean readlnBoolean()
     {
            StringTokenizer string_token_strom =
                        new StringTokenizer( readlnString() );
            String gelesen;
            boolean b_wert = false;

      try
        {
         gelesen = string_token_strom.nextToken();
         b_wert  = uebertrageBooleanToken( gelesen );
        }
      catch ( NoSuchElementException e )
        {
          throw new RuntimeException( "\n\n\tDie eingelesene Zeile enthält KEINE DATEN," +
                                      "\n\tsie ist eine LEERZEILE !!!! \n" 
                                    );
        }
      return b_wert;
     }

/**           <UL><LI> gibt den Eingabe-Aufforderungs-String <CODE>prompt</CODE> aus und
 *                     liest danach einen von white-spaces begrenzten String, der die
 *                     Zeichenfolgen "true, True, T, t, false, False, F, f"
 *                     enthalten darf von der Standardeingabe ein.
 *
 *            <LI>     der Lesezeiger wird hinter das EOLN der aktuellen Zeile
 *                     gestellt, d.h. der Rest der Eingabezeile wird ueberlesen.
 *
 *            </UL><BR> 
 *            @param  prompt ist der Eingabe-Aufforderungs-String
 *            @return den eingelesenen Boolean-Wert
 *            @exception RuntimeException falls Eingabe KEIN Booleanwert wir True|| true || T || t || False || false || F || f ist.
 */

  public static boolean readlnBoolean( String prompt )
     {
      printPrompt ( prompt );
      return readlnBoolean();
     }



/**           <UL><LI> liest ein von white-spaces begrenztes Zeichen von der
 *                     Standardeingabe ein.
 *
 *            <LI>     der Lesezeiger bleibt hinter dem eingelesenen Zeichen
 *                     in der aktuellen Zeile stehen.
 *
 *            </UL><BR> 
 *            @return das eingelesene Zeichen
 *            @exception RuntimeException falls Eingabe KEIN durch white-spaces begrenztes Zeichen eingegeben wurde
 */

  public static char readChar()
     {
            char   c_wert;

      initTokenstrom();
      setzeTokenstromMerkmale( 33 );
      leseToken();
      c_wert  = uebertrageCharToken();
      zuruecksetzenTokenstromMerkmale();
      return c_wert;
     }

/**           <UL><LI> gibt den Eingabe-Aufforderungs-String <CODE>prompt</CODE> aus und
 *                     liest danach ein von white-spaces begrenztes Zeichen von der
 *                     Standardeingabe ein.
 *
 *            <LI>     der Lesezeiger bleibt hinter dem eingelesenen Zeichen
 *                     in der aktuellen Zeile stehen.
 *
 *            </UL><BR> 
 *            @param  prompt ist der Eingabe-Aufforderungs-String
 *            @return das eingelesene Zeichen
 *            @exception RuntimeException falls Eingabe KEIN durch white-spaces begrenztes Zeichen eingegeben wurde
 */

  public static char readChar( String prompt )
     {
      printPrompt ( prompt );
      return readChar();
     }




/**           <UL><LI> liest ein von white-spaces begrenztes Zeichen von der
 *                     Standardeingabe ein.
 *
 *            <LI>     der Lesezeiger wird hinter das EOLN der aktuellen Zeile
 *                     gestellt, d.h. der Rest der Eingabezeile wird ueberlesen.
 *
 *            </UL><BR> 
 *            @return das eingelesene Zeichen
 *            @exception RuntimeException falls Eingabe KEIN durch white-spaces begrenztes Zeichen eingegeben wurde
 */

  public static char readlnChar()
     {
            StringTokenizer string_token_strom =
                        new StringTokenizer( readlnString() );
            String gelesen;
            char   c_wert = ' ';

      try
        {
         gelesen = string_token_strom.nextToken();
         c_wert  = uebertrageCharToken( gelesen );
        }
      catch ( NoSuchElementException e )
        {
          throw new RuntimeException( "\n\n\tDie eingelesene Zeile enthält KEINE DATEN," +
                                      "\n\tsie ist eine LEERZEILE !!!! \n" 
                                    );
        }
      return c_wert;
     }

/**           <UL><LI> gibt den Eingabe-Aufforderungs-String <CODE>prompt</CODE> aus und
 *                     liest danach ein von white-spaces begrenztes Zeichen von der
 *                     Standardeingabe ein.
 *
 *            <LI>     der Lesezeiger wird hinter das EOLN der aktuellen Zeile
 *                     gestellt, d.h. der Rest der Eingabezeile wird ueberlesen.
 *
 *            </UL><BR> 
 *            @param  prompt ist der Eingabe-Aufforderungs-String
 *            @return das eingelesene Zeichen
 *            @exception RuntimeException falls Eingabe KEIN durch white-spaces begrenztes Zeichen eingegeben wurde
 */

  public static char readlnChar( String prompt )
     {
      printPrompt ( prompt );
      return readlnChar();
     }






  private static void printPrompt( String prompt )
     {
      System.out.print ( prompt + " " );
      System.out.flush();
     }


  private static void initTokenstrom()
     {
        if ( !open)
          {
            teiler  = new StreamTokenizer(
                      new BufferedReader(
                      new InputStreamReader( System.in ) ) );
            open = true;

            zuruecksetzenTokenstromMerkmale();
          }
     }


  private static void setzeTokenstromMerkmale( int start )
     {
      teiler.ordinaryChars( start,255 );
      teiler.wordChars( start,255 );
     }



  private static void zuruecksetzenTokenstromMerkmale()
     {
      teiler.resetSyntax();
      teiler.parseNumbers();
      teiler.whitespaceChars( 0, 32 );
      teiler.eolIsSignificant( true );
     }




  private static void leseToken()
     {
        teiler.eolIsSignificant( true );

      try
        {
         teiler.nextToken();

         if ( (teiler.ttype == teiler.TT_EOL) &&
              readMerker
            )
           {
            teiler.nextToken();
            // while ( teiler.nextToken() == teiler.TT_EOL )
            //   ;
           }
         readMerker = true;
        }
      catch (Exception e)
        {
          System.out.println("\n\nKeine Daten Error: " + e.getMessage() );
        }
     }



  private static Double uebertrageNumberToken()
     {
            Double gelesen = new Double( 0.0 );
      try
        {
            gelesen = new Double( readString() );
        }
      catch ( NumberFormatException e )
        {
         erzeugeNumberFormatException();
        }
      return gelesen;
     }



  private static Double uebertrageNumberZeile()
     {
            Double gelesen = new Double( 0.0 );
            StringTokenizer string_token_strom =
                        new StringTokenizer( readlnString() );
            String gelesenStr;

      try
        {
            gelesenStr = string_token_strom.nextToken();
            gelesen = new Double( gelesenStr );
        }
      catch ( NumberFormatException e )
        {
         erzeugeNumberFormatException();
        }
      catch ( NoSuchElementException e )
        {
         throw new RuntimeException( "\n\n\tDie eingelesene Zeile enthält KEINE DATEN," +
                                     "\n\tsie ist eine LEERZEILE !!!! \n" 
                                   );
        }
      return gelesen;
     }



  private static String uebertrageStringToken()
     {
             String hilfe;

      if ( teiler.ttype == teiler.TT_NUMBER )
        {
         hilfe = new String( new Double( teiler.nval ).toString() );
         if ( hilfe.endsWith( ".0" ) )
           {
            hilfe = hilfe.substring( 0, hilfe.length() - 2 );
           }
        }
      else
        {
          // System.out.println( "\n\n-->" + teiler.sval + "<--\n\n" );
          if ( teiler.sval == null )
            {
             if ( teiler.ttype == teiler.TT_EOF )
               {
                hilfe = null;
               }
             else
               {
                // wurde eine LEERE ZEILE gelesen ???
                // if ( teiler.ttype == teiler.TT_EOL )
                hilfe =  new String( "" );
               }
            }
          else
            {
             hilfe =  new String( teiler.sval );
            }
        }
      return hilfe;
     }




  private static char uebertrageCharToken()
     {
            String hilfe;

      hilfe = uebertrageStringToken();

      if ( (hilfe == null) || (hilfe.length() > 1) )
        {
          throw new RuntimeException (
                "\n\n Falsche Char-Eingabe : \n" +
                "Eingelesen      : "+ hilfe + "\n" +
                "Moegliche Werte : alle durch Whitespaces \n" +
                "                  getrennte Zeichen !! \n" );
        }
      return hilfe.charAt(0);
     }



  private static char uebertrageCharToken( String hilfe )
     {

      if ( (hilfe == null) || (hilfe.length() > 1) )
        {
          throw new RuntimeException (
                "\n\n Falsche Char-Eingabe : \n" +
                "Eingelesen      : "+ hilfe + "\n" +
                "Moegliche Werte : alle durch Whitespaces \n" +
                "                  getrennte Zeichen !! \n" );
        }
      return hilfe.charAt(0);
     }



  private static boolean uebertrageBooleanToken( String wert )
     {
              boolean t_wert, f_wert;

      t_wert =  (wert.compareTo( "True"  ) == 0)  ||
                (wert.compareTo( "T"     ) == 0)  ||
                (wert.compareTo( "true"  ) == 0)  ||
                (wert.compareTo( "t"     ) == 0);

      f_wert =  (wert.compareTo( "False" ) == 0)  ||
                (wert.compareTo( "F"     ) == 0)  ||
                (wert.compareTo( "false" ) == 0)  ||
                (wert.compareTo( "f"     ) == 0);

      if ( t_wert )
        {
         return t_wert;
        }
      else
        {
          if ( f_wert )
            {
             return ! f_wert;
            }
          else
            {
             throw new RuntimeException (
                   "\n\n Falsche Boolean-Eingabe : \n" +
                   "Eingelesen      : "+ wert + "\n" +
                   "Moegliche Werte : True,  T, true,  t \n" +
                   "                  False, F, false, f  \n" );
            }
        }
     }


  private static void leseBisEoln()
     {


        try
        {
         teiler.pushBack();
         while ( teiler.ttype != teiler.TT_EOL )
            {
             teiler.nextToken();
             System.out.println( "token_typ = " + teiler.ttype + " == " + teiler.TT_EOL + " token = " + teiler.sval );
            }
        }
      catch (Exception e)
        {
          System.out.println("\n\nKeine Daten Error: " + e.getMessage() );
        }

       }



  private static void werteBereichsUeberpruefung( Double gelesen, char art )
     {
              double max = 0d, min = 0d;
              String typ = new String( "" );

      switch ( art )
        {
         case 'b': max = Byte.MAX_VALUE;
                   min = Byte.MIN_VALUE;
                   typ = new String( "Byte" );
                   break;
         case 's': max = Short.MAX_VALUE;
                   min = Short.MIN_VALUE;
                   typ = new String( "Short" );
                   break;
         case 'i': max = Integer.MAX_VALUE;
                   min = Integer.MIN_VALUE;
                   typ = new String( "Integer" );
                   break;
         case 'l': max = Long.MAX_VALUE;
                   min = Long.MIN_VALUE;
                   typ = new String( "Long" );
                   break;
         case 'f': max = Float.MAX_VALUE;
                   min = Float.MIN_VALUE;
                   typ = new String( "Float" );
                   break;
        }
      if ( art == 'f' )
        {
         if ( (Math.abs(gelesen.doubleValue()) > max) ||
              (Math.abs(gelesen.doubleValue()) < min)
            )
           {
            throw new NumberFormatException (
                  "\n\n"+ typ + "-Zahl Bereichsueberschreitung :\n" +
                  "\tEingelesen     : "+ gelesen.doubleValue() + "\n" +
                  "\tFloatbereich : " +
                  "\n\t -" + max + " <= x <= -" + min + 
                  "\n\t die +/- 0.0 " +
                  "\n\t +" + min + " <= x <= +" + max + 
                  "\n" 
                  );
           }
        }
      else
        {
         if ( (gelesen.doubleValue() > max) ||
              (gelesen.doubleValue() < min)
            )
           {
            throw new NumberFormatException (
                  "\n\n"+ typ + "-Zahl Bereichsueberschreitung :\n" +
                  "\tEingelesen     : "+ gelesen.doubleValue() + "\n" +
                  "\tIntegerbereich : " + min +
                  " <= x <= " + max + "\n" );
           }
        }
     }






  private static void erzeugeNumberFormatException()
     {
       throw new NumberFormatException (
             "\n\n\tKeine Zahl gelesen sondern folgenden String : "
             + teiler.sval + "\n" );
     }


  private static void erzeugeNumberFormatException( String gelesen, char art )
     {
              String typ = new String( "" );

      switch ( art )
        {
         case 'b': typ = new String( "Byte" );
                   break;
         case 's': typ = new String( "Short" );
                   break;
         case 'i': typ = new String( "Integer" );
                   break;
         case 'l': typ = new String( "Long" );
                   break;
         case 'f': typ = new String( "Float" );
                   break;
         case 'd': typ = new String( "Double" );
                   break;
        }

       throw new NumberFormatException (
             "\n\n\tKeine " + typ + "-Zahl gelesen sondern folgenden String : "
             + gelesen + "\n" );
     }

 }
