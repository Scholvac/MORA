/**
 * generated by Xtext 2.19.0
 */
package de.sos.mORA;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.sos.mORA.Parameter#getComplexType <em>Complex Type</em>}</li>
 *   <li>{@link de.sos.mORA.Parameter#getPrimType <em>Prim Type</em>}</li>
 *   <li>{@link de.sos.mORA.Parameter#getProxyType <em>Proxy Type</em>}</li>
 *   <li>{@link de.sos.mORA.Parameter#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see de.sos.mORA.MORAPackage#getParameter()
 * @model
 * @generated
 */
public interface Parameter extends EObject
{
  /**
   * Returns the value of the '<em><b>Complex Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Complex Type</em>' reference.
   * @see #setComplexType(TypeDecl)
   * @see de.sos.mORA.MORAPackage#getParameter_ComplexType()
   * @model
   * @generated
   */
  TypeDecl getComplexType();

  /**
   * Sets the value of the '{@link de.sos.mORA.Parameter#getComplexType <em>Complex Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Complex Type</em>' reference.
   * @see #getComplexType()
   * @generated
   */
  void setComplexType(TypeDecl value);

  /**
   * Returns the value of the '<em><b>Prim Type</b></em>' attribute.
   * The literals are from the enumeration {@link de.sos.mORA.PrimTypeLiteral}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Prim Type</em>' attribute.
   * @see de.sos.mORA.PrimTypeLiteral
   * @see #setPrimType(PrimTypeLiteral)
   * @see de.sos.mORA.MORAPackage#getParameter_PrimType()
   * @model
   * @generated
   */
  PrimTypeLiteral getPrimType();

  /**
   * Sets the value of the '{@link de.sos.mORA.Parameter#getPrimType <em>Prim Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Prim Type</em>' attribute.
   * @see de.sos.mORA.PrimTypeLiteral
   * @see #getPrimType()
   * @generated
   */
  void setPrimType(PrimTypeLiteral value);

  /**
   * Returns the value of the '<em><b>Proxy Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Proxy Type</em>' reference.
   * @see #setProxyType(Interface)
   * @see de.sos.mORA.MORAPackage#getParameter_ProxyType()
   * @model
   * @generated
   */
  Interface getProxyType();

  /**
   * Sets the value of the '{@link de.sos.mORA.Parameter#getProxyType <em>Proxy Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Proxy Type</em>' reference.
   * @see #getProxyType()
   * @generated
   */
  void setProxyType(Interface value);

  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see de.sos.mORA.MORAPackage#getParameter_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link de.sos.mORA.Parameter#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

} // Parameter
