class process
{
	public static void main(String args[]) 
	{
		Runtime run=Runtime.getRuntime();
		String str="java server";
		try
		{
		 Process p1=run.exec(str);
		}
		catch(Exception ex)
		{}
		
	}
}