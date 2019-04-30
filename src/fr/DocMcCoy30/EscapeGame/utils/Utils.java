package fr.DocMcCoy30.EscapeGame.utils;

public class Utils {

    public final static void clearConsole()
    {
        try
        {
            final String os = System.getProperty("os.name");
            System.out.println("os = " + os);
            if (os.contains("Windows 10"))
            {
                System.out.println("cls");
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e)
        {
            //  Handle any exceptions.
        }
    }
}
