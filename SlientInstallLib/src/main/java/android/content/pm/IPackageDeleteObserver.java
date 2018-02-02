/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: F:\\SilentInstallProject\\app\\src\\main\\aidl\\android\\content\\android.content.pm\\IPackageDeleteObserver.aidl
 */
package android.content.pm;
/**
 * API for deletion callbacks from the Package Manager.
 *
 * {@hide}
 */
public interface IPackageDeleteObserver extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements android.content.pm.IPackageDeleteObserver
{
private static final String DESCRIPTOR = "android.content.android.content.pm.IPackageDeleteObserver";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an android.content.android.content.pm.IPackageDeleteObserver interface,
 * generating a proxy if needed.
 */
public static android.content.pm.IPackageDeleteObserver asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof android.content.pm.IPackageDeleteObserver))) {
return ((android.content.pm.IPackageDeleteObserver)iin);
}
return new android.content.pm.IPackageDeleteObserver.Stub.Proxy(obj);
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
case TRANSACTION_packageDeleted:
{
data.enforceInterface(DESCRIPTOR);
boolean _arg0;
_arg0 = (0!=data.readInt());
this.packageDeleted(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements android.content.pm.IPackageDeleteObserver
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
@Override public void packageDeleted(boolean succeeded) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(((succeeded)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_packageDeleted, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_packageDeleted = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public void packageDeleted(boolean succeeded) throws android.os.RemoteException;
}
