package unionset;

public interface DisjointSets {

  public int find(int t);

  public void Union(int a, int b);

  public boolean isConnected(int a, int b);

  public int getSize();
}
