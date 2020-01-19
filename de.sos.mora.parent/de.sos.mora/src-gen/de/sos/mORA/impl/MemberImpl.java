/**
 * generated by Xtext 2.19.0
 */
package de.sos.mORA.impl;

import de.sos.mORA.Annotation;
import de.sos.mORA.MORAPackage;
import de.sos.mORA.Member;
import de.sos.mORA.PrimTypeLiteral;
import de.sos.mORA.TypeDecl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Member</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.sos.mORA.impl.MemberImpl#getDoc <em>Doc</em>}</li>
 *   <li>{@link de.sos.mORA.impl.MemberImpl#getAnno <em>Anno</em>}</li>
 *   <li>{@link de.sos.mORA.impl.MemberImpl#getComplexType <em>Complex Type</em>}</li>
 *   <li>{@link de.sos.mORA.impl.MemberImpl#getPrimType <em>Prim Type</em>}</li>
 *   <li>{@link de.sos.mORA.impl.MemberImpl#getName <em>Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MemberImpl extends MinimalEObjectImpl.Container implements Member
{
  /**
   * The default value of the '{@link #getDoc() <em>Doc</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDoc()
   * @generated
   * @ordered
   */
  protected static final String DOC_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getDoc() <em>Doc</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDoc()
   * @generated
   * @ordered
   */
  protected String doc = DOC_EDEFAULT;

  /**
   * The cached value of the '{@link #getAnno() <em>Anno</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnno()
   * @generated
   * @ordered
   */
  protected EList<Annotation> anno;

  /**
   * The cached value of the '{@link #getComplexType() <em>Complex Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getComplexType()
   * @generated
   * @ordered
   */
  protected TypeDecl complexType;

  /**
   * The default value of the '{@link #getPrimType() <em>Prim Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPrimType()
   * @generated
   * @ordered
   */
  protected static final PrimTypeLiteral PRIM_TYPE_EDEFAULT = PrimTypeLiteral.BOOL;

  /**
   * The cached value of the '{@link #getPrimType() <em>Prim Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPrimType()
   * @generated
   * @ordered
   */
  protected PrimTypeLiteral primType = PRIM_TYPE_EDEFAULT;

  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected MemberImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return MORAPackage.Literals.MEMBER;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getDoc()
  {
    return doc;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setDoc(String newDoc)
  {
    String oldDoc = doc;
    doc = newDoc;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MORAPackage.MEMBER__DOC, oldDoc, doc));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EList<Annotation> getAnno()
  {
    if (anno == null)
    {
      anno = new EObjectContainmentEList<Annotation>(Annotation.class, this, MORAPackage.MEMBER__ANNO);
    }
    return anno;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public TypeDecl getComplexType()
  {
    if (complexType != null && complexType.eIsProxy())
    {
      InternalEObject oldComplexType = (InternalEObject)complexType;
      complexType = (TypeDecl)eResolveProxy(oldComplexType);
      if (complexType != oldComplexType)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, MORAPackage.MEMBER__COMPLEX_TYPE, oldComplexType, complexType));
      }
    }
    return complexType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TypeDecl basicGetComplexType()
  {
    return complexType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setComplexType(TypeDecl newComplexType)
  {
    TypeDecl oldComplexType = complexType;
    complexType = newComplexType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MORAPackage.MEMBER__COMPLEX_TYPE, oldComplexType, complexType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public PrimTypeLiteral getPrimType()
  {
    return primType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setPrimType(PrimTypeLiteral newPrimType)
  {
    PrimTypeLiteral oldPrimType = primType;
    primType = newPrimType == null ? PRIM_TYPE_EDEFAULT : newPrimType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MORAPackage.MEMBER__PRIM_TYPE, oldPrimType, primType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MORAPackage.MEMBER__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case MORAPackage.MEMBER__ANNO:
        return ((InternalEList<?>)getAnno()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case MORAPackage.MEMBER__DOC:
        return getDoc();
      case MORAPackage.MEMBER__ANNO:
        return getAnno();
      case MORAPackage.MEMBER__COMPLEX_TYPE:
        if (resolve) return getComplexType();
        return basicGetComplexType();
      case MORAPackage.MEMBER__PRIM_TYPE:
        return getPrimType();
      case MORAPackage.MEMBER__NAME:
        return getName();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case MORAPackage.MEMBER__DOC:
        setDoc((String)newValue);
        return;
      case MORAPackage.MEMBER__ANNO:
        getAnno().clear();
        getAnno().addAll((Collection<? extends Annotation>)newValue);
        return;
      case MORAPackage.MEMBER__COMPLEX_TYPE:
        setComplexType((TypeDecl)newValue);
        return;
      case MORAPackage.MEMBER__PRIM_TYPE:
        setPrimType((PrimTypeLiteral)newValue);
        return;
      case MORAPackage.MEMBER__NAME:
        setName((String)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case MORAPackage.MEMBER__DOC:
        setDoc(DOC_EDEFAULT);
        return;
      case MORAPackage.MEMBER__ANNO:
        getAnno().clear();
        return;
      case MORAPackage.MEMBER__COMPLEX_TYPE:
        setComplexType((TypeDecl)null);
        return;
      case MORAPackage.MEMBER__PRIM_TYPE:
        setPrimType(PRIM_TYPE_EDEFAULT);
        return;
      case MORAPackage.MEMBER__NAME:
        setName(NAME_EDEFAULT);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case MORAPackage.MEMBER__DOC:
        return DOC_EDEFAULT == null ? doc != null : !DOC_EDEFAULT.equals(doc);
      case MORAPackage.MEMBER__ANNO:
        return anno != null && !anno.isEmpty();
      case MORAPackage.MEMBER__COMPLEX_TYPE:
        return complexType != null;
      case MORAPackage.MEMBER__PRIM_TYPE:
        return primType != PRIM_TYPE_EDEFAULT;
      case MORAPackage.MEMBER__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuilder result = new StringBuilder(super.toString());
    result.append(" (doc: ");
    result.append(doc);
    result.append(", primType: ");
    result.append(primType);
    result.append(", name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //MemberImpl
