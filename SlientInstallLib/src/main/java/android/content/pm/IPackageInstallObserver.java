/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: F:\\SilentInstallProject\\app\\src\\main\\aidl\\android\\content\\android.content.pm\\IPackageInstallObserver.aidl
 */
package android.content.pm;
/**
 * API for installation callbacks from the Package Manager.
 * @hide
 */
public interface IPackageInstallObserver extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements android.content.pm.IPackageInstallObserver
{
private static final String DESCRIPTOR = "android.content.android.content.pm.IPackageInstallObserver";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an android.content.android.content.pm.IPackageInstallObserver interface,
 * generating a proxy if needed.
 */
public static android.content.pm.IPackageInstallObserver asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof android.content.pm.IPackageInstallObserver))) {
return ((android.content.pm.IPackageInstallObserver)iin);
}
return new android.content.pm.IPackageInstallObserver.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_packageInstalled:
{
data.enforceInterface(DESCRIPTOR);
String _arg0;
_arg0 = data.readString();
int _arg1;
_arg1 = data.readInt();
this.packageInstalled(_arg0, _arg1);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements android.content.pm.IPackageInstallObserver
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public void packageInstalled(String packageName, int returnCode) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(packageName);
_data.writeInt(returnCode);
mRemote.transact(Stub.TRANSACTION_packageInstalled, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_packageInstalled = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public void packageInstalled(String packageName, int returnCode) throws android.os.RemoteException;
}
