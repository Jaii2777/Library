package Objects;
public class GroupRequest 
{
	int groupId;
	String requesterName;
	int reqUserId;
	public void setGroupId(int groupId)
	{
		this.groupId=groupId;
	}
	public void setReqUserId(int reqUserId)
	{
		this.reqUserId=reqUserId;
	}
	public void setRequesterName(String requesterName)
	{
		this.requesterName=requesterName;
	}
	public int getGroupId()
	{
		return groupId;
	}
	public int getReqUserId()
	{
		return reqUserId;
	}
	public String getRequesterName()
	{
		return requesterName;
	}
}