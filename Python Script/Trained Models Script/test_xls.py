import openpyxl

doc = openpyxl.load_workbook('modificar_excel.xlsx')

doc.get_sheet_names()

hoja = doc.get_sheet_by_name('Hoja1')

hoja.title

hoja.rows

for filas in hoja.rows:
    print(filas)

hoja['A1'].value

hoja['B1'].value

hoja.cell(row=1,column=1).value

for fila in hoja.rows:
     for columna in fila:
         print(columna.value),
         print("")

hoja['B2'] = 97

doc.save("modificar_excel.xlsx")