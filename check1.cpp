#include <bits/stdc++.h>

typedef unsigned long long ll;
using namespace std;

int main() {
ll t,n,k,a[100001],i,c,z,g;
cin>>t;
g=1;
while(t--){
    cin>>n>>k;
    for(i=0;i<k;i++){
        cin>>a[i];
    }//cout<<a[0];
    sort(a,a+n);
    reverse(a,a+n);
    i=0;
    c=1;
    z=0;
    while (c<=k){
        z+=a[i];
        if (z<n){
            i+=1;
        }
        else{
            break;
        }
        c++;
    }
    if(z>=n){
        cout<<"Scenario #"<<g<<":"<<endl<<c<<endl<<endl;
    }
    else{
        cout<<"Scenario #"<<g<<":"<<endl<<"impossible"<<endl<<endl;
    }g++;
}
return 0;

}