/**
 * generated by Xtext 2.19.0
 */
package de.sos.mORA;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>List Type Decl</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.sos.mORA.ListTypeDecl#getDoc <em>Doc</em>}</li>
 *   <li>{@link de.sos.mORA.ListTypeDecl#getValueType <em>Value Type</em>}</li>
 *   <li>{@link de.sos.mORA.ListTypeDecl#getPrimType <em>Prim Type</em>}</li>
 *   <li>{@link de.sos.mORA.ListTypeDecl#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see de.sos.mORA.MORAPackage#getListTypeDecl()
 * @model
 * @generated
 */
public interface ListTypeDecl extends TypeDecl
{
  /**
   * Returns the value of the '<em><b>Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Doc</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Doc</em>' attribute.
   * @see #setDoc(String)
   * @see de.sos.mORA.MORAPackage#getListTypeDecl_Doc()
   * @model
   * @generated
   */
  String getDoc();

  /**
   * Sets the value of the '{@link de.sos.mORA.ListTypeDecl#getDoc <em>Doc</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Doc</em>' attribute.
   * @see #getDoc()
   * @generated
   */
  void setDoc(String value);

  /**
   * Returns the value of the '<em><b>Value Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Value Type</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value Type</em>' reference.
   * @see #setValueType(SingleTypeDecl)
   * @see de.sos.mORA.MORAPackage#getListTypeDecl_ValueType()
   * @model
   * @generated
   */
  SingleTypeDecl getValueType();

  /**
   * Sets the value of the '{@link de.sos.mORA.ListTypeDecl#getValueType <em>Value Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value Type</em>' reference.
   * @see #getValueType()
   * @generated
   */
  void setValueType(SingleTypeDecl value);

  /**
   * Returns the value of the '<em><b>Prim Type</b></em>' attribute.
   * The literals are from the enumeration {@link de.sos.mORA.PrimTypeLiteral}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Prim Type</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Prim Type</em>' attribute.
   * @see de.sos.mORA.PrimTypeLiteral
   * @see #setPrimType(PrimTypeLiteral)
   * @see de.sos.mORA.MORAPackage#getListTypeDecl_PrimType()
   * @model
   * @generated
   */
  PrimTypeLiteral getPrimType();

  /**
   * Sets the value of the '{@link de.sos.mORA.ListTypeDecl#getPrimType <em>Prim Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Prim Type</em>' attribute.
   * @see de.sos.mORA.PrimTypeLiteral
   * @see #getPrimType()
   * @generated
   */
  void setPrimType(PrimTypeLiteral value);

  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see de.sos.mORA.MORAPackage#getListTypeDecl_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link de.sos.mORA.ListTypeDecl#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

} // ListTypeDecl
