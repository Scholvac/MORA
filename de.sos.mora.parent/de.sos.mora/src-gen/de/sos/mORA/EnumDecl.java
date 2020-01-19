/**
 * generated by Xtext 2.19.0
 */
package de.sos.mORA;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Enum Decl</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.sos.mORA.EnumDecl#getDoc <em>Doc</em>}</li>
 *   <li>{@link de.sos.mORA.EnumDecl#getName <em>Name</em>}</li>
 *   <li>{@link de.sos.mORA.EnumDecl#getLiterals <em>Literals</em>}</li>
 * </ul>
 *
 * @see de.sos.mORA.MORAPackage#getEnumDecl()
 * @model
 * @generated
 */
public interface EnumDecl extends SingleTypeDecl
{
  /**
   * Returns the value of the '<em><b>Doc</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Doc</em>' attribute.
   * @see #setDoc(String)
   * @see de.sos.mORA.MORAPackage#getEnumDecl_Doc()
   * @model
   * @generated
   */
  String getDoc();

  /**
   * Sets the value of the '{@link de.sos.mORA.EnumDecl#getDoc <em>Doc</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Doc</em>' attribute.
   * @see #getDoc()
   * @generated
   */
  void setDoc(String value);

  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see de.sos.mORA.MORAPackage#getEnumDecl_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link de.sos.mORA.EnumDecl#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Literals</b></em>' containment reference list.
   * The list contents are of type {@link de.sos.mORA.Literal}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Literals</em>' containment reference list.
   * @see de.sos.mORA.MORAPackage#getEnumDecl_Literals()
   * @model containment="true"
   * @generated
   */
  EList<Literal> getLiterals();

} // EnumDecl
