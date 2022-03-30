package application;

import persistence.RecipePersistence;
import persistence.hsqldb.RecipePersistenceHSQLDB;

public class Services
{
	private static RecipePersistence recipePersistence = null;

	public static synchronized RecipePersistence getRecipePersistence()
    {
		if (recipePersistence == null)
		{
		    //recipePersistence = new RecipePersistenceStub();
            recipePersistence = new RecipePersistenceHSQLDB(Main.getDBPathName());
        }

        return recipePersistence;
	}
}
